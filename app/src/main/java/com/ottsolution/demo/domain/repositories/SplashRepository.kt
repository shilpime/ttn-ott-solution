package com.ottsolution.demo.domain.repositories

import com.ottsolution.demo.data.networking.models.ConfigResponse
import com.ottsolution.demo.data.networking.models.requests.ConfigRequest
import io.reactivex.Single

/**
 * Created by Srikant Karnani on 21/8/19.
 */
interface SplashRepository {
    fun getConfigData(configRequest: ConfigRequest): Single<ConfigResponse>
}