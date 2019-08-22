package com.ottsolution.demo.data.repository

import com.ottsolution.demo.data.networking.models.VodDetailResponse
import com.ottsolution.demo.data.networking.services.DetailService
import com.ottsolution.demo.domain.repositories.DetailRepository
import io.reactivex.Single

class DetailRepoImpl (private val configService: DetailService) : DetailRepository {
    override fun vodeDetailRequest(): Single<VodDetailResponse> {
        return configService.vodeDetailRequest("https://api.myjson.com/bins/ck2yf")
    }

}