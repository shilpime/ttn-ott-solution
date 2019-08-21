package com.ottsolution.demo.domain.repositories

import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.data.networking.models.requests.HomeRequest
import io.reactivex.Single

interface UserAccountRepository {
    fun getHomePage(request: HomeRequest): Single<HomeResponse>
}