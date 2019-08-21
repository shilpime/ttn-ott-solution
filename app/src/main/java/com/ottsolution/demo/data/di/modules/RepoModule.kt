package com.ottsolution.demo.data.di.modules

import com.ottsolution.demo.data.networking.services.ConfigService
import com.ottsolution.demo.data.networking.services.UserAccountService
import com.ottsolution.demo.data.repository.SplashRepoImpl
import com.ottsolution.demo.data.repository.UserAccountRepoImpl
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
    fun provideAccountRepo(userAccountService: UserAccountService): UserAccountRepository {
        return UserAccountRepoImpl(userAccountService)
    }

    @Singleton
    @Provides
    fun provideSplashRepo(configService: ConfigService): SplashRepository {
        return SplashRepoImpl(configService)
    }
}