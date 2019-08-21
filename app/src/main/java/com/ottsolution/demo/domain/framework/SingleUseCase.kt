package com.ottsolution.demo.domain.framework

import io.reactivex.Single


interface SingleUseCase<T> {
    fun execute(): Single<T>
}
