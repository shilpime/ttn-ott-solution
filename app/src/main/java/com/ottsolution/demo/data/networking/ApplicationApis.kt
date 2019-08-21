package com.ottsolution.demo.data.networking

import com.ottsolution.demo.data.networking.models.HomeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface ApplicationApis {

    @GET("/home-content")
    fun getHomePage(@Query("userId") userId:String): Single<HomeResponse>

}