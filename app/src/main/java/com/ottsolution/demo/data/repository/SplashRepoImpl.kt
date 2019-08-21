package com.ottsolution.demo.data.repository

import com.ottsolution.demo.data.networking.models.ConfigResponse
import com.ottsolution.demo.data.networking.models.requests.ConfigRequest
import com.ottsolution.demo.data.networking.services.ConfigService
import com.ottsolution.demo.domain.repositories.SplashRepository
import io.reactivex.Single

/**
 * Created by Srikant Karnani on 21/8/19.
 */
class SplashRepoImpl(private val configService: ConfigService) : SplashRepository {
    override fun getConfigData(configRequest: ConfigRequest): Single<ConfigResponse> {
        return configService.getConfigData("https://api.myjson.com/bins/u8fwn")
    }

}