package com.ottsolution.demo.ui.features.player

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util.getUserAgent
import com.ottsolution.demo.R
import com.ottsolution.demo.databinding.PlayerFragmentBinding
import com.ottsolution.demo.ui.base.frameworks.base.BaseFragment
import kotlinx.android.synthetic.main.player_fragment.*


class PlayerFragment : BaseFragment<PlayerFragmentBinding, PlayerViewModel>() {
    override fun getViewModelClass(): Class<PlayerViewModel> {
        return PlayerViewModel::class.java
    }

    override fun layoutId(): Int {
        return R.layout.player_fragment
    }

    var mPlayer: ExoPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPlayer()
        hideSystemUi()
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        player.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun initPlayer() {
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory()
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        mPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
        player.useController = true
        player.requestFocus()
        player.player = mPlayer
        val uri = Uri.parse(getString(R.string.sample_url))
        val mediaSource = buildMediaSource(uri)
        Log.e("onInit", "${viewModel.currentWindow}")
        Log.e("onInit", "${viewModel.playbackPosition}")
        mPlayer!!.prepare(mediaSource)
        mPlayer!!.playWhenReady = viewModel.playWhenReady
        mPlayer!!.seekTo(viewModel.currentWindow, viewModel.playbackPosition)
    }


    private fun buildMediaSource(uri: Uri): MediaSource {
        val defaultBandwidthMeter = DefaultBandwidthMeter.Builder(context)
        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            getUserAgent(context, "TTNOtt"),
            defaultBandwidthMeter.build()
        )
        return HlsMediaSource.Factory(dataSourceFactory)
            .setAllowChunklessPreparation(true)
            .createMediaSource(uri)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        releasePlayer()
    }

    private fun releasePlayer() {
        if (mPlayer != null) {
            viewModel.playbackPosition = mPlayer!!.currentPosition
            viewModel.currentWindow = mPlayer!!.currentWindowIndex
            viewModel.playWhenReady = mPlayer!!.playWhenReady
            mPlayer!!.release()
            mPlayer = null
            Log.e("onInit", "${viewModel.currentWindow}")
            Log.e("onInit", "${viewModel.playbackPosition}")
        }
    }

}
