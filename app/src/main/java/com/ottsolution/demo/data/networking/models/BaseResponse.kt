package com.ottsolution.demo.data.networking.models

import com.google.gson.annotations.SerializedName

open class BaseResponse{


    @SerializedName("code")
    var code: Int = 0
    @SerializedName("message")
    var message: String? = null

    object CODE{
        const val INVALID_409 = 409
        const val OK_200 = 200
    }
}