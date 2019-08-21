package com.ottsolution.demo.ui.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.data.networking.models.requests.HomeRequest
import com.ottsolution.demo.domain.usecase.HomePageUseCase
import com.ottsolution.demo.ui.base.frameworks.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homePageUseCase: HomePageUseCase
) : BaseViewModel() {
    private val homeResponse = MutableLiveData<HomeResponse>()

    init {
        fetchHomeResponse()
    }

    private fun fetchHomeResponse() {
        val request = HomeRequest("")
        val disposable = homePageUseCase.execute(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if(it.data == null){
                        setError(Throwable(it.message))
                    }else {
                        homeResponse.value = it
                    }
                },
                {
                    setError(Throwable("Our server is acting up! Please try again in some time"))
                }
            )
        addDisposable(disposable)
    }

    fun getHomeData():LiveData<HomeResponse>{
        return homeResponse
    }
}