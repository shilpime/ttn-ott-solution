package com.ottsolution.demo.data.di.modules

import com.ottsolution.demo.data.networking.services.ConfigService
import com.ottsolution.demo.data.networking.services.DetailService
import com.ottsolution.demo.data.networking.services.HomePageService
import com.ottsolution.demo.data.repository.DetailRepoImpl
import com.ottsolution.demo.data.repository.SplashRepoImpl
import com.ottsolution.demo.data.repository.UserAccountRepoImpl
import com.ottsolution.demo.domain.repositories.DetailRepository
import com.ottsolution.demo.domain.repositories.SplashRepository
import com.ottsolution.demo.domain.repositories.UserAccountRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    /**
     * Provide all the Repositories from here
     */
    @Singleton
    @Provides
    fun provideAccountRepo(userAccountService: HomePageService): UserAccountRepository {
        return UserAccountRepoImpl(userAccountService)
    }

    @Singleton
    @Provides
    fun provideSplashRepo(configService: ConfigService): SplashRepository {
        return SplashRepoImpl(configService)
    }

    @Singleton
    @Provides
    fun provideDetailsRepo(detailService: DetailService) : DetailRepository{
        return DetailRepoImpl(detailService)
    }
}