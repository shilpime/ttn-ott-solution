package com.ottsolution.demo.data.networking.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList


class HomeResponse: BaseResponse() {
    @SerializedName("data")
    var data: Data? = null


    class Data {

        @SerializedName("total")
        @Expose
        var total: Int = 0

        @SerializedName("offset")
        @Expose
        var offset: Int = 0

        @SerializedName("limit")
        @Expose
        var limit: String? = null

        @SerializedName("items")
        @Expose
        var items: List<Items>? = null

    }

    class Items{

        var viewType: Int = 0

        @SerializedName("id")
        var id: Int = 0

        @SerializedName("title")
        var title: String = ""

        @SerializedName("sectionType")
        var sectionType: String = ""

        @SerializedName("layoutType")
        var layoutType: String = ""

        @SerializedName("contentList")
        lateinit var contentList: ArrayList<ContentList>

        @SerializedName("totalCount")
        var totalCount: Int = 0

        @SerializedName("autoScroll")
        var isAutoScroll: Boolean = false
    }
    class ContentList() : Parcelable {
        @SerializedName("id")
        var id: Int = 0
        @SerializedName("contentId")
        var contentId: String = ""
        @SerializedName("title")
        var title: String = ""
        @SerializedName("boxCoverImage")
        var boxCoverImage: String = ""
        @SerializedName("thumbnailImage")
        var thumbnailImage: String = ""
        @SerializedName("image")
        var image: String = ""
        @SerializedName("contentType")
        var contentType: String = ""
        @SerializedName("genres")
        lateinit var genres: Array<String>
        @SerializedName("linkUrl")
        var linkUrl: String = ""
        @SerializedName("selfCareScreen")
        var selfCareScreen: String = ""
        @SerializedName("subTitles")
        lateinit var subsTitle: Array<String>
        @SerializedName("logo")
        var logo: String = ""
        @SerializedName("airedDate")
        var airedDate: String = ""
        @SerializedName("contractName")
        var contractName: String = ""
        @SerializedName("entitlements")
        lateinit var entitlements: Array<String>
        @SerializedName("channelName")
        var channelName: String = ""
        @SerializedName("duration")
        var duration: String = ""
        @SerializedName("epgState")
        var epgState: String = ""
        @SerializedName("channelId")
        var channelId: String = ""
        @SerializedName("secondsWatched")
        var secondsWatched: Int = 0
        @SerializedName("durationInSeconds")
        var durationInSeconds: Int = 0
        @SerializedName("vodId")
        var vodId: String = ""
        @SerializedName("isKidsViewable")
        var isKidsViewable: Boolean = false
        @SerializedName("language")
        lateinit var language: Array<String>
        @SerializedName("lastWatched")
        var lastWatched: Long = 0
        @SerializedName("displayDate")
        var displayDate: String = ""
        @SerializedName("url")
        var url: String = ""
        @SerializedName("downloadProgress")
        var downloadProgress: Int = 0
        @SerializedName("sid")
        var sid: String = ""
        @SerializedName("mbr")
        var mbr: String = ""
        @SerializedName("campaignID")
        var campaignID: String = ""
        @SerializedName("layoutType")
        var layoutType: String = ""
        @SerializedName(value = "categoryType", alternate = ["contentCategory"])
        var categoryType: String = ""

        constructor(parcel: Parcel) : this() {
            id = parcel.readInt()
            contentId = parcel.readString().toString()
            title = parcel.readString().toString()
            boxCoverImage = parcel.readString().toString()
            thumbnailImage = parcel.readString().toString()
            image = parcel.readString().toString()
            contentType = parcel.readString().toString()
            genres = parcel.createStringArray() as Array<String>
            linkUrl = parcel.readString().toString()
            selfCareScreen = parcel.readString().toString()
            subsTitle = parcel.createStringArray() as Array<String>
            logo = parcel.readString().toString()
            airedDate = parcel.readString().toString()
            contractName = parcel.readString().toString()
            entitlements = parcel.createStringArray() as Array<String>
            channelName = parcel.readString().toString()
            duration = parcel.readString().toString()
            epgState = parcel.readString().toString()
            channelId = parcel.readString().toString()
            secondsWatched = parcel.readInt()
            durationInSeconds = parcel.readInt()
            vodId = parcel.readString().toString()
            isKidsViewable = parcel.readByte() != 0.toByte()
            language = parcel.createStringArray() as Array<String>
            lastWatched = parcel.readLong()
            displayDate = parcel.readString().toString()
            url = parcel.readString().toString()
            downloadProgress = parcel.readInt()
            sid = parcel.readString().toString()
            mbr = parcel.readString().toString()
            campaignID = parcel.readString().toString()
            layoutType = parcel.readString().toString()
            categoryType = parcel.readString().toString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(contentId)
            parcel.writeString(title)
            parcel.writeString(boxCoverImage)
            parcel.writeString(thumbnailImage)
            parcel.writeString(image)
            parcel.writeString(contentType)
            parcel.writeStringArray(genres)
            parcel.writeString(linkUrl)
            parcel.writeString(selfCareScreen)
            parcel.writeStringArray(subsTitle)
            parcel.writeString(logo)
            parcel.writeString(airedDate)
            parcel.writeString(contractName)
            parcel.writeStringArray(entitlements)
            parcel.writeString(channelName)
            parcel.writeString(duration)
            parcel.writeString(epgState)
            parcel.writeString(channelId)
            parcel.writeInt(secondsWatched)
            parcel.writeInt(durationInSeconds)
            parcel.writeString(vodId)
            parcel.writeByte(if (isKidsViewable) 1 else 0)
            parcel.writeStringArray(language)
            parcel.writeLong(lastWatched)
            parcel.writeString(displayDate)
            parcel.writeString(url)
            parcel.writeInt(downloadProgress)
            parcel.writeString(sid)
            parcel.writeString(mbr)
            parcel.writeString(campaignID)
            parcel.writeString(layoutType)
            parcel.writeString(categoryType)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ContentList> {
            override fun createFromParcel(parcel: Parcel): ContentList {
                return ContentList(parcel)
            }

            override fun newArray(size: Int): Array<ContentList?> {
                return arrayOfNulls(size)
            }
        }
    }
}