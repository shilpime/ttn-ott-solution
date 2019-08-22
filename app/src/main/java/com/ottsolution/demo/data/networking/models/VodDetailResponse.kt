package com.ottsolution.demo.data.networking.models

import com.google.gson.annotations.SerializedName

class VodDetailResponse : BaseResponse() {
    @SerializedName("data")
    lateinit var data: Data

    class Data {

        @SerializedName("meta")
        lateinit var meta: MetaData

        @SerializedName("detail")
        lateinit var detail: MetaDetails

    }
    class MetaDetails{
        @SerializedName("playUrl")
        var playUrl: String = ""
    }
    class MetaData {
        @SerializedName("id")
        var id: String = ""

        @SerializedName("title")
        var title: String = ""

        @SerializedName("boxCoverImage")
        var boxCoverImage: String = ""

        @SerializedName("posterImage")
        var posterImage: String = ""

        @SerializedName("genre")
        var genre: Array<String> = emptyArray()

        @SerializedName("duration")
        var duration: String = ""

        @SerializedName("releaseYear")
        var releaseYear: String = ""

        @SerializedName("description")
        var description: String = ""

        @SerializedName("actor")
        var actor: Array<String> = emptyArray()

        @SerializedName("director")
        var director: Array<String> = emptyArray()

        @SerializedName("producer")
        var producer: Array<String> = emptyArray()

        @SerializedName("writer")
        var writer: Array<String> = emptyArray()

        @SerializedName("rating")
        var rating: String = ""

        @SerializedName("audio")
        var audio: Array<String> = emptyArray()

        @SerializedName("expiry")
        var expiry: String = ""

        @SerializedName("downloadExpiry")
        var downloadExpiry: Int = 0

        @SerializedName("contentType")
        var contentType: String = ""

        @SerializedName("vodAssetId")
        var vodAssetId: String = ""

        @SerializedName("favourite")
        var favourite: Boolean = false

        @SerializedName("downloadable")
        var downloadable: Boolean = false

        @SerializedName("hd")
        var hd: Boolean = false

        @SerializedName("allowedForKids")
        var allowedForKids: Boolean = false

    }
}