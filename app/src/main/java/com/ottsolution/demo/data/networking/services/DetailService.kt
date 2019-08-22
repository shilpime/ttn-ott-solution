package com.ottsolution.demo.data.networking.services

import com.ottsolution.demo.data.networking.ApplicationApis
import com.ottsolution.demo.data.networking.models.VodDetailResponse
import io.reactivex.Single

class DetailService (private val applicationApis: ApplicationApis) {

    fun vodeDetailRequest(request: String): Single<VodDetailResponse> {
        return applicationApis.mockVodDetail(request)
    }
}