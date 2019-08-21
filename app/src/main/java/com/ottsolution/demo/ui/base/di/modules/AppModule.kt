package com.ottsolution.demo.ui.base.di.modules

import android.app.Application
import android.content.Context
import com.ottsolution.demo.data.di.modules.NetworkModule
import com.ottsolution.demo.data.di.modules.RepoModule
import com.ottsolution.demo.data.di.modules.SharedPrefsModule
import com.ottsolution.demo.ui.base.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        (NetworkModule::class),
        (RepoModule::class),
        (UseCaseModule::class),
        (ViewModelModule::class),
        (SharedPrefsModule::class)
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}