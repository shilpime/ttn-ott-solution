package com.ottsolution.demo.ui.base

import android.content.Context
import com.ottsolution.demo.data.di.modules.NetworkModule
import com.ottsolution.demo.ui.base.di.AppComponent
import com.ottsolution.demo.ui.base.frameworks.base.BaseApplication
import com.ottsolution.demo.BuildConfig
import com.ottsolution.demo.R
import com.ottsolution.demo.ui.base.di.DaggerAppComponent
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class MyApp: BaseApplication() {


    companion object {
        var context: Context? = null
            private set
            get

    }
    override fun onCreate() {
        super.onCreate()
        context = this
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.default_font))
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

    override val appComponent: AppComponent by lazy { DaggerAppComponent.builder()
        .application(this)
        .network(NetworkModule(BuildConfig.BASE_URL))
        .build()
    }
}