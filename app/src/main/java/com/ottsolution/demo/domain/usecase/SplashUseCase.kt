package com.ottsolution.demo.domain.usecase

import com.ottsolution.demo.data.networking.models.ConfigResponse
import com.ottsolution.demo.data.networking.models.requests.ConfigRequest
import com.ottsolution.demo.domain.framework.SingleUseCaseWithParameter
import com.ottsolution.demo.domain.repositories.SplashRepository
import io.reactivex.Single

/**
 * Created by Srikant Karnani on 21/8/19.
 */

class SplashUseCase(private val repository: SplashRepository):
    SingleUseCaseWithParameter<ConfigResponse, ConfigRequest> {

    override fun execute(parameter: ConfigRequest): Single<ConfigResponse> {
        return repository.getConfigData(parameter)
    }
}
