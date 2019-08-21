package com.ottsolution.demo.data.networking.services

import com.ottsolution.demo.data.networking.ApplicationApis
import com.ottsolution.demo.data.networking.models.ConfigResponse
import com.ottsolution.demo.data.networking.models.requests.ConfigRequest
import io.reactivex.Single
import javax.inject.Singleton

/**
 * Created by Srikant Karnani on 21/8/19.
 */

@Singleton
class ConfigService(private val applicationApis: ApplicationApis) {

    fun getConfigData(request: String):Single<ConfigResponse>{
        return applicationApis.mockConfig(request)
    }
}