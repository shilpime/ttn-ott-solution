package com.ottsolution.demo.ui.features.home


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.NonNull
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ottsolution.demo.R
import com.ottsolution.demo.customviews.RVLinearLayoutManager
import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.databinding.FragmentHomeBinding
import com.ottsolution.demo.interfaces.CommonDTOClickListener
import com.ottsolution.demo.ui.base.frameworks.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


/**
 * The homePageUseCase screen of the app which displays all the products
 *
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_toolbar_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        viewModel.getHomeData().observe(this, Observer { onHomeResponseFetched(it) })
    }

    private fun onHomeResponseFetched(homeResponse: HomeResponse) {
        //
        val animator = object : DefaultItemAnimator() {
            override fun canReuseUpdatedViewHolder(@NonNull viewHolder: RecyclerView.ViewHolder): Boolean {
                return true
            }
        }
        val manager = RVLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val mRailAdapter = HomeAdapter(homeResponse.data!!.items, activity, mBannerClick)
        homeRecyclerView.setItemAnimator(animator)
        homeRecyclerView.setLayoutManager(manager)
        homeRecyclerView.setAdapter(mRailAdapter)
        homeRecyclerView.post({ mRailAdapter.notifyDataSetChanged() })
    }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun layoutId(): Int = R.layout.fragment_home
}

object mBannerClick : CommonDTOClickListener {
    override fun onSubItemClick(
        iPairList: ArrayList<Pair<View, String>>,
        iListItem: HomeResponse.ContentList,
        iItemPosition: Int,
        iSectionPosition: Int,
        iSectionTitle: String
    ) {
        //
    }

}
