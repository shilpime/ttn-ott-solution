package com.ottsolution.demo.ui.features.splash

import android.os.Bundle
import android.view.View
import com.ottsolution.demo.R
import com.ottsolution.demo.ui.base.frameworks.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    override fun getContentViewId(): Int = R.layout.activity_splash

    override fun getRootLayoutContainer(): View = splash_container

}
