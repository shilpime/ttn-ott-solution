package com.ottsolution.demo.ui.features.player

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ottsolution.demo.R
import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.data.networking.models.VodDetailResponse
import com.ottsolution.demo.ui.base.frameworks.base.BaseActivity
import kotlinx.android.synthetic.main.activity_player.*

/**
 * Created by Srikant Karnani on 21/8/19.
 */
class PlayerActivity: BaseActivity<PlayerViewModel>() {

    private var commonDTO: HomeResponse.ContentList? = null
    internal var mPlayerFragment: PlayerFragment? = null
    private var detailsFragment: DetailsFragment? = null

    override fun getContentViewId(): Int {
        return R.layout.activity_player
    }

    override fun getRootLayoutContainer(): View {
        return root_container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateDetailScreen()
        inflatePlayerScreen()
        fetchDetail()
    }

    private fun fetchDetail() {

        viewModel = ViewModelProviders.of(this, viewModelFactory)[PlayerViewModel::class.java]
        viewModel.fetchDetail()
        viewModel.getDetailResponse().observe(this, Observer { onDetailFetched(it) })

    }

    private fun onDetailFetched(it: VodDetailResponse?) {
        Log.e("PlayerActivity","Inside Details Fetched")
        blank_page.visibility = View.GONE
        video_details_container.visibility = View.VISIBLE
        player_container.visibility = View.VISIBLE
        detailsFragment!!.onDetailFetched(it)
    }

    private fun inflateDetailScreen() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        detailsFragment = DetailsFragment()//.newInstance(commonDTO)
        detailsFragment!!.setListener(this)
        detailsFragment!!.setRetainInstance(true)
        transaction.add(R.id.video_details_container, detailsFragment!!)
        transaction.commit()
    }
    private fun inflatePlayerScreen() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        mPlayerFragment = PlayerFragment()
        mPlayerFragment!!.setRetainInstance(true)
        transaction.add(R.id.player_container, mPlayerFragment!!)
        transaction.commit()
    }

}