package com.ottsolution.demo.domain.framework

import io.reactivex.Single

interface SingleUseCaseWithParameter<R, P> {
    fun execute(parameter: P): Single<R>
}
