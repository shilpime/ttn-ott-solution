package com.ottsolution.demo.data.networking.services

import com.ottsolution.demo.data.networking.ApplicationApis
import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.data.networking.models.requests.HomeRequest
import io.reactivex.Single
import javax.inject.Singleton


@Singleton
class HomePageService(private val applicationApis: ApplicationApis) {


    fun getHomePage(request: String): Single<HomeResponse> {
        return applicationApis.mockHomePage(request)
    }
}