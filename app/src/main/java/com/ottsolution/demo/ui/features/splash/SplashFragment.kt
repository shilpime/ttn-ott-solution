package com.ottsolution.demo.ui.features.splash

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.ottsolution.demo.R
import com.ottsolution.demo.data.networking.models.ConfigResponse
import com.ottsolution.demo.databinding.SplashFragmentBinding
import com.ottsolution.demo.ui.base.frameworks.base.BaseActivity
import com.ottsolution.demo.ui.base.frameworks.base.BaseFragment
import com.ottsolution.demo.ui.features.home.HomeActivity
import kotlinx.android.synthetic.main.splash_fragment.*
import java.util.*
import kotlin.random.Random

class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {

    override fun getViewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun layoutId(): Int = R.layout.splash_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        viewModel.configResponse.observe(this, Observer {
            onConfigDataFetched(it)
        })
        initView()
    }

    private fun onConfigDataFetched(configData: ConfigResponse) {
        videoView.setOnCompletionListener { onCompletedCheck(configData) }
    }

    private fun initView() {
        try {
            videoView.setMediaController(null)
            val path = "android.resource://" + (activity as BaseActivity).packageName + "/" + R.raw.splash
            videoView.setVideoURI(Uri.parse(path))
            videoView.start()
            videoView.setOnPreparedListener {
                it.setVolume(0f, 0f);
            }
        } catch (e: Exception) {
            Log.e("", e.message, e)
        }
    }

    private fun onCompletedCheck(configData: ConfigResponse) {
        activity?.let {
            val intent = Intent(it, HomeActivity::class.java)

            val randomTheme = configData.data!!.display!!.android!!.theme//"theme-" + Random.nextInt(1,6)
//            intent.putExtra("theme", configData.data?.display?.android?.theme ?: randomTheme)
            intent.putExtra("theme", randomTheme)
            intent.putExtra("background", configData.data?.display?.android?.background ?: "default")
            it.startActivity(intent)
            it.finish()
        }
    }
}
