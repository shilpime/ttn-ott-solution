package com.ottsolution.demo.interfaces

import android.view.View
import com.ottsolution.demo.data.networking.models.HomeResponse
import java.util.ArrayList
import androidx.core.util.Pair

interface CommonDTOClickListener {
    abstract fun onSubItemClick(
        iPairList: ArrayList<Pair<View, String>>,
        iListItem: HomeResponse.ContentItem,
        iItemPosition: Int,
        iSectionPosition: Int,
        iSectionTitle: String
    )

}