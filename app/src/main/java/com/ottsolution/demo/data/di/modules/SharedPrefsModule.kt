package com.ottsolution.demo.data.di.modules

import android.content.Context
import com.ottsolution.demo.data.prefs.SharedPrefs
import com.ottsolution.demo.domain.repositories.PrefsRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SharedPrefsModule {

    @Singleton
    @Provides
    fun provideSharedPrefs(context: Context): SharedPrefs {
        return SharedPrefs(context)
    }

    @Singleton
    @Provides
    fun providePrefsRepo(context: Context): PrefsRepo {
        return SharedPrefs(context)
    }
}