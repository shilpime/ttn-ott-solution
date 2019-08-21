package com.ottsolution.demo.domain.usecase


import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.data.networking.models.requests.HomeRequest
import com.ottsolution.demo.domain.framework.SingleUseCaseWithParameter
import com.ottsolution.demo.domain.repositories.UserAccountRepository
import io.reactivex.Single


class HomePageUseCase(private val userAccountRepository: UserAccountRepository):
    SingleUseCaseWithParameter<HomeResponse, HomeRequest> {

    override fun execute(parameter: HomeRequest): Single<HomeResponse> {
        return userAccountRepository.getHomePage(parameter)
    }
}