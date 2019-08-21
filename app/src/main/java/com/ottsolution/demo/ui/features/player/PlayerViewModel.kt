package com.ottsolution.demo.ui.features.player

import com.ottsolution.demo.ui.base.frameworks.base.BaseViewModel
import javax.inject.Inject

class PlayerViewModel @Inject constructor() : BaseViewModel() {
    var currentWindow = 0
    var playbackPosition = 0L
    var playWhenReady = true

}
