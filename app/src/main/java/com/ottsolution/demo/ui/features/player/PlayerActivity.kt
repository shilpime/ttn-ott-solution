package com.ottsolution.demo.ui.features.player

import android.view.View
import com.ottsolution.demo.R
import com.ottsolution.demo.ui.base.frameworks.base.BaseActivity
import kotlinx.android.synthetic.main.activity_player.*

/**
 * Created by Srikant Karnani on 21/8/19.
 */
class PlayerActivity: BaseActivity() {
    override fun getContentViewId(): Int {
        return R.layout.activity_player
    }

    override fun getRootLayoutContainer(): View {
        return root_container
    }
}