package com.ottsolution.demo.utils.imagepicker

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import java.io.*

object ImagePicker {

    private val TEMP_IMAGE_NAME = "temp.png"
    private val TAG = "ImagePickerLog"
    const val CAMERA_INTENT = 1
    public const val GALLERY_INTENT = 2
    public const val FILE_INTENT = 3

    private lateinit var mCurrentCameraPhotoPath:String

    public val pickImageGalleryIntent: Intent
        get() = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )


    fun getPickImageCameraIntent(mContext: Context): Intent {
        //code to create camera intent
        val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (cameraInt.resolveActivity(mContext.packageManager) !=
            null
        ) {

            val photoURI = FileProvider.getUriForFile(
                mContext,
                mContext.packageName + ".fileprovider",
                getTempFile(mContext)!!.apply {
                    mCurrentCameraPhotoPath = absolutePath
                }
            )

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                cameraInt.clipData = ClipData.newRawUri("", photoURI)
                cameraInt.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            if (photoURI != null) {
                cameraInt.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            }
        }

        return cameraInt
    }

    private fun getPickFilesIntent(activity: Activity): Intent? {
        Log.d(TAG, "getPickFilesIntent: Inside HPiker")
        val mimeType = "*/*"
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = mimeType
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        // special intent for Samsung file manager
        val sIntent = Intent("com.sec.android.app.myfiles.PICK_DATA")
        sIntent.putExtra("CONTENT_TYPE", mimeType)
        sIntent.addCategory(Intent.CATEGORY_DEFAULT)
        sIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)

//        if (activity.getPackageManager().resolveActivity(sIntent, 0) != null) {
        //
        //            // it is device with samsung file manager
        //            chooserIntent = Intent.createChooser(sIntent,
        //                    activity.getString(R.string.image_picker_open_file));
        //            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
        //                    new Intent[] { intent});
        //        } else {
        //
        //            chooserIntent = Intent.createChooser(intent,
        //                    activity.getString(R.string.image_picker_open_file));
        //        }

        return null
    }

    //create temp file in android directory
    private fun getTempFile(context: Context): File? {
        return createImageFile(context)
    }

    fun onActivityResult(
        mContext: Context,
        requestCode: Int,
        resultCode: Int,
        data: Intent,
        mCallback: OnImagePicked
    ) {

        if (resultCode == Activity.RESULT_OK) {
            var bm: Bitmap?
            var fileInstance:File?
            val selectedFileUri: Uri?
            when (requestCode) {

                CAMERA_INTENT -> {
                    fileInstance = File(mCurrentCameraPhotoPath)
                    selectedFileUri = Uri.fromFile(fileInstance)
                    //scale down version
                    //bm = getImageResized(mContext, selectedFileUri);
                    val rotation = getRotation(mContext, selectedFileUri, true)
                    //DebugLog.e("Image Orientation: "+rotation);
                    bm = decodeBitmap(mCurrentCameraPhotoPath)

                    //handle image orientation
                    if (bm != null) {
                        bm = rotate(bm, rotation)
                        //save fixed orientation bitmap into file
                        saveBitmap(bm, fileInstance)
                        mCallback.onSuccess(fileInstance, bm)
                    } else
                        mCallback.onError()
                }

                GALLERY_INTENT -> {
                    selectedFileUri = data.data
                    fileInstance = getRealPathFromURI(mContext, selectedFileUri)

                    if (fileInstance != null) {
                        //scale down version
                        //bm = getImageResized(mContext, selectedFileUri);
                        bm = decodeFile(fileInstance)

                        if (bm != null) {
                            //handle image orientation
                            bm = rotate(
                                bm,
                                getRotation(mContext, selectedFileUri, false)
                            )
                            mCallback.onSuccess(fileInstance, bm)
                        } else
                            mCallback.onError()
                    } else
                        mCallback.onError()
                }

                FILE_INTENT -> {
                    Log.d(TAG, "onActivityResult: Inside HPiker")
                    selectedFileUri = data.data
                    fileInstance = getRealPathFromURI(mContext, selectedFileUri)

                    if (fileInstance != null)
                        mCallback.onFileSuccess(fileInstance)
                    else
                        mCallback.onError()
                }
            }
        }
    }

    private fun decodeBitmap(photoPath: String): Bitmap?{
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        options.inSampleSize = 8
        return BitmapFactory.decodeFile(photoPath, options)
    }

    // Decodes image and scales it to reduce memory consumption
    private fun decodeFile(f: File?): Bitmap? {
        try {
            // Decode image size
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true

            // The new size we want to scale to
            val REQUIRED_SIZE = 100

            // Find the correct scale value. It should be the power of 2.
            var scale = 1
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2
            }

            o.inSampleSize = scale
            val bitmap = BitmapFactory.decodeStream(
                FileInputStream(f), null,
                o
            )

            saveBitmap(bitmap, f)
            return bitmap
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return null
    }

    private fun saveBitmap(bm: Bitmap?, file: File?) {
        val outStream: OutputStream
        try {
            outStream = FileOutputStream(file!!)
            bm!!.compress(Bitmap.CompressFormat.PNG, 100, outStream)
            outStream.flush()
            outStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    //to get rotation of bitmap
    private fun getRotation(context: Context, imageUri: Uri?, isCamera: Boolean): Int {
        val rotation: Int
        if (isCamera) {
            rotation = getRotationFromCamera(context, imageUri)
        } else {
            rotation = getRotationFromGallery(context, imageUri)
        }
        Log.d(TAG, "Image rotation: $rotation")
        return rotation
    }

    //to get rotation of bitmap capture mFrom camera
    private fun getRotationFromCamera(context: Context, imageFile: Uri?): Int {
        var rotate = 0
        try {
            context.contentResolver.notifyChange(imageFile!!, null)
            val exif = ExifInterface(imageFile.path!!)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return rotate
    }

    //to get rotation of bitmap picked mFrom gallery
    private fun getRotationFromGallery(context: Context, imageUri: Uri?): Int {
        var result = 0
        val columns = arrayOf(MediaStore.Images.Media.ORIENTATION)
        var cursor: Cursor? = null
        try {
            cursor = context.contentResolver.query(
                imageUri!!,
                columns, null, null, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val orientationColumnIndex = cursor.getColumnIndex(columns[0])
                result = cursor.getInt(orientationColumnIndex)
            }
        } catch (e: Exception) {
            //Do nothing
        } finally {
            cursor?.close()
        }//End of try-catch block
        return result
    }

    //to rotate bitmap
    private fun rotate(bm: Bitmap, rotation: Int): Bitmap {
        if (rotation != 0) {
            val matrix = Matrix()
            matrix.postRotate(rotation.toFloat())
            return Bitmap.createBitmap(
                bm, 0, 0,
                bm.width,
                bm.height,
                matrix,
                true
            )
        }
        return bm
    }


    abstract class OnImagePicked {

        abstract fun onSuccess(imageFile: File?, bm: Bitmap?)
        fun onFileSuccess(imageFile: File) {}
        fun onError() {}

    }


    /****************************UNSED */
    //Resize to avoid using too much memory loading big images (e.g.: 2560*1920)
    private fun getImageResized(context: Context, selectedImage: Uri): Bitmap? {
        var bm: Bitmap? = null

        try {
            val sampleSizes = intArrayOf(8, 5, 2, 1)
            var i = 0
            val minWidthQuality = 300
            do {
                bm = decodeBitmap(context, selectedImage, sampleSizes[i])

                Log.d(TAG, "ReSizer: new bitmap width = " + bm!!.width)
                i++
            } while (bm!!.width < minWidthQuality && i < sampleSizes.size)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bm
    }

    //decode bitmap and manipulate there sample
    private fun decodeBitmap(context: Context, theUri: Uri, sampleSize: Int): Bitmap? {

        val options = BitmapFactory.Options()
        options.inSampleSize = sampleSize

        var fileDescriptor: AssetFileDescriptor? = null
        try {
            fileDescriptor = context.contentResolver
                .openAssetFileDescriptor(
                    theUri,
                    "r"
                )
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        var actuallyUsableBitmap: Bitmap? = null
        if (fileDescriptor != null) {
            actuallyUsableBitmap = BitmapFactory.decodeFileDescriptor(
                fileDescriptor.fileDescriptor, null,
                options
            )

            Log.d(
                TAG, options.inSampleSize.toString() + " sample method bitmap ... " +
                        actuallyUsableBitmap!!.width + " " + actuallyUsableBitmap.height
            )
        }

        return actuallyUsableBitmap
    }
}
