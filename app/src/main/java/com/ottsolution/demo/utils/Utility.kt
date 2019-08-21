package com.ottsolution.demo.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import com.ottsolution.demo.ui.base.MyApp
import com.ottsolution.demo.R
import com.ottsolution.demo.data.prefs.SharedPrefs


fun getRealDisplayPoint(context: Context): Point {
    val size = Point()
    try {
        val display = (context as Activity).windowManager.defaultDisplay
        display.getRealSize(size)
        return size
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return size
}
fun isTablet() : Boolean{
    return isTablet(MyApp.context)
}

fun isTablet(iContext: Context?): Boolean {
    return iContext == null || !iContext.resources.getBoolean(R.bool.portrait_only)
}


fun dpToPx(iContext: Context, dp: Int): Int {
    val density = iContext.resources.displayMetrics.density
    return Math.round(dp.toFloat() * density)
}


fun getCloudineryUrl(url: String, width: Int, height: Int): String {
    val cloudineryUrl = SharedPrefs.getString(MyApp.context, SharedPrefs.PREF_KEY_CLOUDENARY_URL)

    val w = "w_$width"
    val h = ",h_$height"
    val style = ",c_scale/"

    if (cloudineryUrl == null || cloudineryUrl.toString().isEmpty()) {
        return url
    }
    val curl: String
    if (width < 600) {
        curl = "$cloudineryUrl$w$h,f_webp,q_auto:eco$style$url"
    } else {
        curl = "$cloudineryUrl$w$h$,f_webp,q_auto$style$url"
    }
    //        Logger.d("image url", curl);
    return curl
}
fun getRoundedCloudnaryUrl(url: String, width: Int, height: Int): String {
    val cloudineryUrl = SharedPrefs.getString(MyApp.context, SharedPrefs.PREF_KEY_CLOUDENARY_URL)

    val w = "w_$width"
    val h = ",h_$height"
    val style = ",f_webp,q_auto:low/"
    val r = ",r_max"

    return if (cloudineryUrl == null || cloudineryUrl.toString().isEmpty()) {
        url
    } else "$cloudineryUrl$w$h$r$style$url"

//        Logger.d("image url logo", curl);
}