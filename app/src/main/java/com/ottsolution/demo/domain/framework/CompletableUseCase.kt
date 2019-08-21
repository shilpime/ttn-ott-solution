package com.ottsolution.demo.domain.framework

import io.reactivex.Completable


interface CompletableUseCase {
    fun execute(): Completable
}