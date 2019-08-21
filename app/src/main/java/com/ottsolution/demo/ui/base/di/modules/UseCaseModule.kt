package com.ottsolution.demo.ui.base.di.modules

import com.ottsolution.demo.domain.repositories.SplashRepository
import com.ottsolution.demo.domain.repositories.UserAccountRepository
import com.ottsolution.demo.domain.usecase.HomePageUseCase
import com.ottsolution.demo.domain.usecase.SplashUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideApprovalUseCase(userAccountRepository: UserAccountRepository): HomePageUseCase {
        return HomePageUseCase(userAccountRepository)
    }

    @Provides
    fun provideConfigUseCase(splashRepository: SplashRepository): SplashUseCase{
        return SplashUseCase(splashRepository)
    }
}