package com.ottsolution.demo.data.networking.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ConfigResponse  : BaseResponse() {

    @SerializedName("data")
    @Expose
    var data: Data? = null

    class Data {

        @SerializedName("app")
        @Expose
        var app: App? = null

        @SerializedName("config")
        @Expose
        var config: Config? = null

        @SerializedName("display")
        @Expose
        var display: Display? = null

    }
    class Display{
        @SerializedName("android")
        @Expose
        var android: AndroidDisplay? = null

    }
    class AndroidDisplay{

        @SerializedName("theme")
        @Expose
        var theme: String? = null

        @SerializedName("background")
        @Expose
        var background: String? = null

        @SerializedName("font")
        @Expose
        var font: String? = null

        @SerializedName("logo")
        @Expose
        var logo: String? = null
    }

    class Hd {

        @SerializedName("low")
        @Expose
        var low: Int? = null
        @SerializedName("medium")
        @Expose
        var medium: Int? = null
        @SerializedName("high")
        @Expose
        var high: Int? = null
        @SerializedName("def")
        @Expose
        var def: Int? = null

    }

    class Hd_ {

        @SerializedName("low")
        @Expose
        var low: Int? = null
        @SerializedName("medium")
        @Expose
        var medium: Int? = null
        @SerializedName("high")
        @Expose
        var high: Int? = null
        @SerializedName("def")
        @Expose
        var def: Int? = null

    }

    class IOS {

        @SerializedName("recommendedVersion")
        @Expose
        var recommendedVersion: String? = null
        @SerializedName("forceUpgradeVersion")
        @Expose
        var forceUpgradeVersion: String? = null

    }

    class Image {

        @SerializedName("cloudAccountUrl")
        @Expose
        var cloudAccountUrl: String? = null
        @SerializedName("cloudSubAccountUrl")
        @Expose
        var cloudSubAccountUrl: String? = null

    }

    class Live {

        @SerializedName("sd")
        @Expose
        var sd: Sd_? = null
        @SerializedName("hd")
        @Expose
        var hd: Hd_? = null

    }

    class Sd {

        @SerializedName("low")
        @Expose
        var low: Int? = null
        @SerializedName("medium")
        @Expose
        var medium: Int? = null
        @SerializedName("high")
        @Expose
        var high: Int? = null
        @SerializedName("def")
        @Expose
        var def: Int? = null

    }

    class Sd_ {

        @SerializedName("low")
        @Expose
        var low: Int? = null
        @SerializedName("medium")
        @Expose
        var medium: Int? = null
        @SerializedName("high")
        @Expose
        var high: Int? = null
        @SerializedName("def")
        @Expose
        var def: Int? = null

    }


    class TermsCondition

    class Url {

        @SerializedName("eulaUrl")
        @Expose
        var eulaUrl: String? = null
        @SerializedName("image")
        @Expose
        var image: Image? = null
        @SerializedName("helpUrl")
        @Expose
        var helpUrl: String? = null
        @SerializedName("termsConditionsUrl")
        @Expose
        var termsConditionsUrl: String? = null
        @SerializedName("privacyPolicyUrl")
        @Expose
        var privacyPolicyUrl: String? = null
        @SerializedName("traiUrl")
        @Expose
        var traiUrl: String? = null
    }

    class Vod {

        @SerializedName("sd")
        @Expose
        var sd: Sd? = null
        @SerializedName("hd")
        @Expose
        var hd: Hd? = null

    }

    class Android {

        @SerializedName("recommendedVersion")
        @Expose
        var recommendedVersion: String? = null
        @SerializedName("forceUpgradeVersion")
        @Expose
        var forceUpgradeVersion: String? = null

    }

    class App {

        @SerializedName("appUpgrade")
        @Expose
        var appUpgrade: AppUpgrade? = null

    }

    class AppUpgrade {

        @SerializedName("android")
        @Expose
        var android: Android? = null
        @SerializedName("iOS")
        @Expose
        var iOS: IOS? = null

    }

    class Bitrate {


        @SerializedName("vod")
        @Expose
        var vod: Vod? = null
        @SerializedName("live")
        @Expose
        var live: Live? = null

    }

    class Config {

        @SerializedName("bitrate")
        @Expose
        var bitrate: Bitrate? = null

        @SerializedName("dl_position")
        @Expose
        var dlPosition: Int? = null

        @SerializedName("cw_interval")
        @Expose
        var cwInterval: Int? = null


        @SerializedName("terms_condition")
        @Expose
        var termsCondition: TermsCondition? = null
        @SerializedName("url")
        @Expose
        var url: Url? = null

        @SerializedName("cw_position")
        @Expose
        var cw_position: Int? = null
    }
}