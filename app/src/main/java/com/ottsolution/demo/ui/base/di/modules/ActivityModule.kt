package com.ottsolution.demo.ui.base.di.modules

import com.ottsolution.demo.ui.base.di.scopes.ActivityScope
import com.ottsolution.demo.ui.features.home.HomeActivity
import com.ottsolution.demo.ui.features.player.PlayerActivity
import com.ottsolution.demo.ui.features.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    @ActivityScope
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    @ActivityScope
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    @ActivityScope
    abstract fun playerActivity(): PlayerActivity
}