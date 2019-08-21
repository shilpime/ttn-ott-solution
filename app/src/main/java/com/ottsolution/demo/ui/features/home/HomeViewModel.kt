package com.ottsolution.demo.ui.features.home

import com.ottsolution.demo.domain.repositories.PrefsRepo
import com.ottsolution.demo.domain.usecase.HomePageUseCase
import com.ottsolution.demo.ui.base.frameworks.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val emiratesIDUseCase: HomePageUseCase,
    val sharedPrefs: PrefsRepo
) : BaseViewModel() {

}