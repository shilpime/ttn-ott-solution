package com.ottsolution.demo.ui.base.di.modules

import com.ottsolution.demo.ui.base.di.scopes.PerFragment
import com.ottsolution.demo.ui.features.home.HomeFragment
import com.ottsolution.demo.ui.features.player.PlayerFragment
import com.ottsolution.demo.ui.features.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentModule {

    @ContributesAndroidInjector
    @PerFragment
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun splashFragment(): SplashFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun playerFragment(): PlayerFragment

}