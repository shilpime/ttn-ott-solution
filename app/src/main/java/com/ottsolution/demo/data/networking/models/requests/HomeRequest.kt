package com.ottsolution.demo.data.networking.models.requests

import com.google.gson.annotations.SerializedName

data class HomeRequest(@SerializedName("userId") val userId: String)