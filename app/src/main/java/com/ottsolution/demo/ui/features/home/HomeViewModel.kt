package com.ottsolution.demo.ui.features.home

import androidx.lifecycle.MutableLiveData
import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.data.networking.models.requests.HomeRequest
import com.ottsolution.demo.domain.repositories.PrefsRepo
import com.ottsolution.demo.domain.usecase.HomePageUseCase
import com.ottsolution.demo.ui.base.frameworks.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val homePageUseCase: HomePageUseCase
) : BaseViewModel() {
    val userBasicInfo = MutableLiveData<HomeResponse>()
    /**
     * Validates the emirates ID entered by the user with the help of server
     * and gets the basic info of the user based on the ID
     */
    fun fetchHomeResponse() {
        val request = HomeRequest("")
        val disposable = homePageUseCase.execute(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if(it.data == null){
                        setError(Throwable(it.message))
                    }else {
                        userBasicInfo.value = it
                    }
                },
                {
                    setError(Throwable("Our server is acting up! Please try again in some time"))
                }
            )
        addDisposable(disposable)
    }
}