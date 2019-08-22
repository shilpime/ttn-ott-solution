package com.ottsolution.demo.domain.repositories

import com.ottsolution.demo.data.networking.models.VodDetailResponse
import io.reactivex.Single

interface DetailRepository {
    fun vodeDetailRequest(): Single<VodDetailResponse>
}