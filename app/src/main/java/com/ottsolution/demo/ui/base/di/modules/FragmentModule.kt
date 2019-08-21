package com.ottsolution.demo.ui.base.di.modules

import com.ottsolution.demo.ui.base.di.scopes.PerFragment
import com.ottsolution.demo.ui.features.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentModule {

    @ContributesAndroidInjector
    @PerFragment
    abstract fun homeFragment(): HomeFragment

}