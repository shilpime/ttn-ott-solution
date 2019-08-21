package com.ottsolution.demo.ui.base.di.modules

import com.ottsolution.demo.ui.base.di.scopes.ActivityScope
import com.ottsolution.demo.ui.features.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    @ActivityScope
    abstract fun homeActivity(): HomeActivity
}