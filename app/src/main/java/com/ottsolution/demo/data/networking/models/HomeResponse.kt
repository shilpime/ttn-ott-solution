package com.ottsolution.demo.data.networking.models

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
    class ContentList{
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
    }
}