package com.ottsolution.demo.ui.features.home

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.ottsolution.demo.ui.base.frameworks.base.BaseActivity
import com.ottsolution.demo.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * The navigation controller is the NavHostFragment added to this activity's XML
         * This will show the Fragment associated with this action - R.id.action_sign_in.
         * Checkout nav_home.xml in navigation folder of resources.
         */
//        findNavController(R.id.fragment_container).navigate(R.id.action_home_landing)
        setSupportActionBar(toolbar_layout.findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    /**
     * ID of the XML Content view to be set for this activity
     */
    override fun getContentViewId(): Int = R.layout.activity_home

    /**
     * Root layout of this activity
     */
    override fun getRootLayoutContainer(): View = root_container
}

