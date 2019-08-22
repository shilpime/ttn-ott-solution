package com.ottsolution.demo.ui.features.player

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.ottsolution.demo.R
import com.ottsolution.demo.data.networking.models.VodDetailResponse
import com.ottsolution.demo.databinding.DetailPageFreeBinding
import com.ottsolution.demo.ui.base.frameworks.base.BaseFragment
import kotlinx.android.synthetic.main.common_detail.*
import kotlinx.android.synthetic.main.detail_page_free.*

class DetailsFragment : BaseFragment<DetailPageFreeBinding, PlayerViewModel>(){
    override fun getViewModelClass(): Class<PlayerViewModel> {
        return PlayerViewModel::class.java
    }

    override fun layoutId(): Int {
        return R.layout.detail_page_free
    }

    fun setListener(context: Context) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun onDetailFetched(it: VodDetailResponse?) {
        Log.e("DetailsFragment","Inside Details Fetched")
        populateDataInViews(it!!.data.meta)
    }

    private fun populateDataInViews(meta: VodDetailResponse.MetaData?) {
        var releaseYear = ""
        var genre = ""
        var duration = meta!!.duration
        if (meta != null && meta!!.genre != null && meta!!.genre.size > 0) {
            genre = meta!!.genre[0]
        }
        if (meta != null && meta!!.releaseYear != null) {
            releaseYear = meta!!.releaseYear
        }
        var audio = ""
        if (meta!!.audio != null && meta!!.audio.size > 0) {
            audio = TextUtils.join(" | ", meta.audio)
            audio = "  $audio  |"
        }
        if (!meta!!.duration.equals("0")) {
            vod_subtitle.text = "$genre  |$audio  $releaseYear  |  $duration min"
        } else
            vod_subtitle.text = "$releaseYear | $genre"

        vod_title.text = meta.title

        // Desc Row
        if (!TextUtils.isEmpty(meta!!.description)) {
            detail_desc.text = meta!!.description
            detail_desc.visibility = View.VISIBLE
            key_desc.visibility = View.VISIBLE
            line.visibility = View.VISIBLE
        } else {
            detail_desc.visibility = View.GONE
            key_desc.visibility = View.GONE
            line.visibility = View.GONE
        }

        // Director Row
        if (meta!!.director != null && meta!!.director.size > 0) {
            row_director.visibility = View.VISIBLE
//            director.setText(Utility.lineSeparatedString(meta!!.director))
        } else
            row_director.visibility = View.GONE

        // Actor Row
        if (meta!!.actor != null && meta!!.actor.size > 0) {
            row_starring.visibility = View.VISIBLE
//            detailStarring.setText(Utility.lineSeparatedString(meta!!.actor))
        } else
            row_starring.setVisibility(View.GONE)

        //Producer Row
        if (meta!!.producer != null && meta!!.producer.size > 0) {
            row_producer.visibility = View.VISIBLE
//            producer.setText(Utility.lineSeparatedString(meta!!.producer))
        } else
            row_producer.visibility = View.GONE

        // Audio Row
        if (meta!!.audio != null && meta!!.audio.size > 0) {
            row_audio.visibility = View.VISIBLE
//            detailAudio.setText(Utility.lineSeparatedString(meta!!.audio))
        } else
            row_audio.setVisibility(View.GONE)

        // Hide Expiry for VOD Contents
        row_expiry.setVisibility(View.GONE)
//
//        if (Utility.loggedIn() && Utility.isTablet())
//            keyDesc.setVisibility(View.GONE)

    }



//    public fun newInstance(commonDTO: HomeResponse.ContentList): DetailsFragment {
//        val detailsFragment = DetailsFragment()
//        val bundle = Bundle()
//        bundle.putParcelable("DTO", commonDTO)
//        detailsFragment.arguments = bundle
//        return detailsFragment
//    }

}