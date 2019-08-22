package com.ottsolution.demo.domain.usecase

import com.ottsolution.demo.data.networking.models.VodDetailResponse
import com.ottsolution.demo.data.networking.models.requests.DetailRequest
import com.ottsolution.demo.domain.framework.SingleUseCaseWithParameter
import com.ottsolution.demo.domain.repositories.DetailRepository
import io.reactivex.Single

class DetailUseCase (private val repository: DetailRepository):
    SingleUseCaseWithParameter<VodDetailResponse, DetailRequest> {
    override fun execute(parameter: DetailRequest): Single<VodDetailResponse> {
        return repository.vodeDetailRequest()
    }
}
