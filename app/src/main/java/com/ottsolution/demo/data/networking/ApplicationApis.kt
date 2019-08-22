package com.ottsolution.demo.data.networking

import com.ottsolution.demo.data.networking.models.ConfigResponse
import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.data.networking.models.VodDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import javax.inject.Singleton


@Singleton
interface ApplicationApis {

    @GET("/api/v1/config")
    fun configData(): Single<ConfigResponse>

    @GET("/api/v1/homePageUseCase")//https://api.myjson.com/bins/fjwwf
    fun getHomePage(@Query("userId") userId: String): Single<HomeResponse>

    @GET
    fun mockHomePage(@Url url: String): Single<HomeResponse>

    @GET
    fun mockConfig(@Url url: String): Single<ConfigResponse>

    @GET
    fun mockVodDetail(@Url url: String): Single<VodDetailResponse>
}