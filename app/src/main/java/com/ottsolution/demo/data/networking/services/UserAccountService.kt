package com.ottsolution.demo.data.networking.services

import com.ottsolution.demo.data.networking.ApplicationApis
import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.data.networking.models.requests.HomeRequest
import io.reactivex.Single
import javax.inject.Singleton


@Singleton
class UserAccountService(private val applicationApis: ApplicationApis) {


    fun getHomePage(request: HomeRequest): Single<HomeResponse> {
        return applicationApis.getHomePage(request.userId)
    }
}