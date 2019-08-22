package com.ottsolution.demo.ui.features.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ottsolution.demo.data.networking.models.VodDetailResponse
import com.ottsolution.demo.data.networking.models.requests.DetailRequest
import com.ottsolution.demo.domain.usecase.DetailUseCase
import com.ottsolution.demo.ui.base.frameworks.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayerViewModel @Inject constructor(private val detailUseCase: DetailUseCase)
    : BaseViewModel() {

    private val detailResponse = MutableLiveData<VodDetailResponse>()
    init {
        //fetchDetail()
    }

    fun fetchDetail() {
        val request = DetailRequest()
        val disposable = detailUseCase.execute(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if(it.data == null){
                        setError(Throwable(it.message))
                    }else {
                        detailResponse.value = it
                    }
                },
                {
                    setError(Throwable("Our server is acting up! Please try again in some time"))
                }
            )
        addDisposable(disposable)
    }


    fun getDetailResponse(): LiveData<VodDetailResponse> {
        return detailResponse
    }

    var currentWindow = 0
    var playbackPosition = 0L
    var playWhenReady = true

}
