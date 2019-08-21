package com.ottsolution.demo.ui.base.di.modules

import com.ottsolution.demo.domain.repositories.UserAccountRepository
import com.ottsolution.demo.domain.usecase.HomePageUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideApprovalUseCase(userAccountRepository: UserAccountRepository): HomePageUseCase {
        return HomePageUseCase(userAccountRepository)
    }
}