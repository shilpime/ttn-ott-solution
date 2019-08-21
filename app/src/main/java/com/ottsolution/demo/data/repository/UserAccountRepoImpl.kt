package com.ottsolution.demo.data.repository

import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.data.networking.models.requests.HomeRequest
import com.ottsolution.demo.data.networking.services.UserAccountService
import com.ottsolution.demo.domain.repositories.UserAccountRepository
import io.reactivex.Single


class UserAccountRepoImpl(private val service: UserAccountService): UserAccountRepository {
    override fun getHomePage(request: HomeRequest): Single<HomeResponse> {
        //return service.getHomePage(request)
        return service.getHomePage("https://api.myjson.com/bins/fjwwf")
    }

}