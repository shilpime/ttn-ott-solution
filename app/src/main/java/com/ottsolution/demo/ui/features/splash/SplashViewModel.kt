package com.ottsolution.demo.ui.features.splash

import androidx.lifecycle.MutableLiveData
import com.ottsolution.demo.data.networking.models.ConfigResponse
import com.ottsolution.demo.data.networking.models.requests.ConfigRequest
import com.ottsolution.demo.domain.usecase.SplashUseCase
import com.ottsolution.demo.ui.base.frameworks.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val useCase: SplashUseCase
) : BaseViewModel() {
    val configResponse = MutableLiveData<ConfigResponse>()

    init {
        fetchConfigResponse()
    }

    private fun fetchConfigResponse() {
        val request = ConfigRequest()
        val disposable = useCase.execute(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.data == null) {
                        setError(Throwable(it.message))
                    } else {
                        configResponse.postValue(it)
                    }
                },
                {
                    setError(Throwable("Our server is acting up! Please try again in some time"))
                }
            )
        addDisposable(disposable)
    }
}
