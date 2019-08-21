package com.ottsolution.demo.ui.base.frameworks.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ottsolution.demo.ui.base.frameworks.SingleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel : ViewModel() {

    private var _errorMessage = MediatorLiveData<SingleEvent<Throwable>>()
    private var _userMessage = MediatorLiveData<SingleEvent<String>>()

    val errorMessage: LiveData<SingleEvent<Throwable>>
        get() = _errorMessage

    val userMessage: LiveData<SingleEvent<String>>
        get() = _userMessage

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }

    protected fun setError(error: Throwable) {
        _errorMessage.postValue(SingleEvent(error))
    }

    public fun setMessage(message: String){
        _userMessage.postValue(SingleEvent(message))
    }

}