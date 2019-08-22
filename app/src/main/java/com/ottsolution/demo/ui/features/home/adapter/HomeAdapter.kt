package com.ottsolution.demo.ui.features.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ottsolution.demo.R
import com.ottsolution.demo.customviews.EndlessListAdapter
import com.ottsolution.demo.customviews.HomeHeroView
import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.databinding.HomeRecyclerviewBinding
import com.ottsolution.demo.interfaces.CommonDTOClickListener
import com.ottsolution.demo.ui.features.home.ItemViewType
import com.ottsolution.demo.ui.features.home.model.RailsModel


class HomeAdapter(
    private val mList: List<HomeResponse.Items>,
    private val mBannerClick: CommonDTOClickListener
) : EndlessListAdapter<HomeResponse.Items, RecyclerView.ViewHolder>(mList) {
    //    private RecyclerViewClickItem mSeeAllListener;
    private val mSharedPoolPot = RecyclerView.RecycledViewPool()
    private val mSharedPoolLan = RecyclerView.RecycledViewPool()
    private var reset: Boolean = false
    private var mBanner: ViewHolderBanner? = null
    private val HERO_BANNER = 1000
    private val ALL_CHANNELS = 2000
    private val RAIL = 3000
    private val CONTINUE_WATCHING = 4000
    private val WIDGET = 5000


    override fun getNoContentVisibility(): Int {
        return View.GONE
    }

    override fun createNormalViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {

        val view: View
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            HERO_BANNER -> {
                view = HomeHeroView(parent.context)
                mBanner = ViewHolderBanner(view)
                viewHolder = mBanner
                parent.tag = null
            }
            ALL_CHANNELS -> {
                val allChannel = RecyclerView(parent.context)
                allChannel.layoutManager = LinearLayoutManager(parent.context, LinearLayoutManager.HORIZONTAL, false)
                //                allChannel.addItemDecoration(decoration);
                view = allChannel
                parent.tag = null
                viewHolder = ViewHolderAllChannel(view)
            }
            RAIL -> {
                view = inflater.inflate(R.layout.home_recyclerview, parent, false)
                viewHolder = ViewHolderRails(view)
            }
            CONTINUE_WATCHING -> {
                view = inflater.inflate(R.layout.home_recyclerview, parent, false)
                viewHolder = ViewHolderRails(view)
            }
            WIDGET -> {
                view = inflater.inflate(R.layout.home_recyclerview, parent, false)
                viewHolder = ViewHolderRails(view)
            }
        }


        return viewHolder
    }

    override fun clear() {
        super.clear()
        reset = true
    }

    override fun bindNormalViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        when (holder.itemViewType) {
            HERO_BANNER -> {
                val bannerViewHolder = holder as ViewHolderBanner
                when {
                    bannerViewHolder.banner.len == 0 -> bannerViewHolder.banner.initData(item, mBannerClick)
                    reset -> {
                        bannerViewHolder.banner.reset()
                        bannerViewHolder.banner.initData(item, mBannerClick)
                        reset = false
                    }
                    else -> bannerViewHolder.banner.startSlide(true)
                }
                updateHeroSlideBehaviour(item.isAutoScroll)
            }
            ALL_CHANNELS -> {
                val railsViewHolder = holder as ViewHolderRails
                railsViewHolder.bind(RailsModel(item.title, RailAdapter(item.contentList)))
            }
            RAIL -> {
                val railsViewHolder = holder as ViewHolderRails
                railsViewHolder.bind(RailsModel(item.title, RailAdapter(item.contentList)))
            }
            CONTINUE_WATCHING -> {

            }
            WIDGET -> {

            }
        }//                item = mList.get(position);
        //                ViewHolderAllChannel allChannelViewHolder = (ViewHolderAllChannel) holder;
        //
        //                if (allChannelViewHolder.allChannels.getAdapter() == null)
        //                    allChannelViewHolder.allChannels.setAdapter(new AllChannelLiveTVAdapter(mActivity, item.getCommonDTO(), mListener, position, item.getTitle()));
        //                item = mList.get(position);
        //                ViewHolder viewHolderRail = (ViewHolder) holder;
        //
        //                viewHolderRail.mBinding.setTitle(item.getTitle());
        //                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity,
        //                        LinearLayoutManager.HORIZONTAL, false);
        //                viewHolderRail.mBinding.homeRecyclerView.setLayoutManager(linearLayoutManager);
        //                float v = (mList.get(position).getLayoutType().equalsIgnoreCase(AppConstants.LANDSCAPE_MODE)) ? AppConstants.LANDSCAPE_RATIO : AppConstants.PORTRAIT_RATIO;
        //                viewHolderRail.mBinding.homeRecyclerView.setRecycledViewPool(mList.get(position).getLayoutType().equalsIgnoreCase(AppConstants.LANDSCAPE_MODE) ? mSharedPoolLan : mSharedPoolPot);
        //                viewHolderRail.mBinding.homeSeeAll.setOnClickListener(new View.OnClickListener() {
        //                    @Override
        //                    public void onClick(View view) {
        //                        if (Utility.isNetworkConnected()) {
        //                            mSeeAllListener.onItemClicked(position, item);
        //                        } else {
        //                            Utility.showToast(mActivity, mActivity.getResources().getString(R.string.no_internet_connection));
        //                        }
        //
        ////                        mSeeAllListener.onItemClicked(position, item);
        //
        //                    }
        //                });
        //                viewHolderRail.mBinding.homeRecyclerView.setAdapter(new VODShowListAdapter(item.getCommonDTO(), v, mItemClickedListener, mActivity, position, item.getTitle(), OnDemandTvShowsChildFragment.TYPE_TV_SHOWS, item.getSectionType()));
        //                item = mList.get(position);
        //                ViewHolder viewHolder = (ViewHolder) holder;
        //
        //                viewHolder.mBinding.setTitle(item.getTitle());
        //                LinearLayoutManager hManager = new LinearLayoutManager(mActivity,
        //                        LinearLayoutManager.HORIZONTAL, false);
        //                viewHolder.mBinding.homeRecyclerView.setLayoutManager(hManager);
        //                float mode = (mList.get(position).getLayoutType().equalsIgnoreCase(AppConstants.LANDSCAPE_MODE)) ? AppConstants.LANDSCAPE_RATIO : AppConstants.PORTRAIT_RATIO;
        //                viewHolder.mBinding.homeRecyclerView.setRecycledViewPool(mList.get(position).getLayoutType().equalsIgnoreCase(AppConstants.LANDSCAPE_MODE) ? mSharedPoolLan : mSharedPoolPot);
        //                viewHolder.mBinding.homeSeeAll.setOnClickListener(new View.OnClickListener() {
        //                    @Override
        //                    public void onClick(View view) {
        //                        if (Utility.isNetworkConnected()) {
        //                            mSeeAllListener.onItemClicked(position, item);
        //                        } else {
        //                            Utility.showToast(mActivity, mActivity.getResources().getString(R.string.no_internet_connection));
        //                        }
        //                    }
        //                });
        //
        //
        //                continueWatchingAdapter = new VODShowListAdapter(item.getCommonDTO(), mode, mItemClickedListener, mActivity, position, item.getTitle(), OnDemandTvShowsChildFragment.TYPE_TV_SHOWS, item.getSectionType());
        //
        //                viewHolder.mBinding.homeRecyclerView.setAdapter(continueWatchingAdapter);
        //                SharedPreference.setInt(TataSkyApp.getContext(), AppConstants.PREF_KEY_CONTINUE_WATCHING_POSITION, position);
        //                item = mList.get(position);
        //                LinearLayoutManager lm = new LinearLayoutManager(mActivity,
        //                        LinearLayoutManager.HORIZONTAL, false);
        //
        //                ViewHolder widgetViewHolder = (ViewHolder) holder;
        //                widgetViewHolder.mBinding.divider.setVisibility(View.GONE);
        //                widgetViewHolder.mBinding.homeRecyclerViewTitle.setVisibility(View.GONE);
        //                widgetViewHolder.mBinding.homeSeeAll.setVisibility(View.GONE);
        //                widgetViewHolder.mBinding.homeRecyclerView.setLayoutManager(lm);
        //                widgetViewHolder.mBinding.homeRecyclerView.setAdapter(new WidgetAdapter(mActivity));
        //                widgetViewHolder.mBinding.setTitle(item.getTitle());
    }

    private fun updateHeroSlideBehaviour(pauseSlide: Boolean) {
        if (mBanner != null) {
            if (pauseSlide) {
                mBanner!!.banner.stopSlide()
            } else {
                mBanner!!.banner.startSlide(false)
            }
        }
    }

//    fun notifyItemForPosition(item: HomeResponse.Items) {
//        if (continueWatchingAdapter != null) {
//            continueWatchingAdapter.refresh(item.getCommonDTO());
//        }
//    }

    inner class ViewHolderRails(itemView: View) :
        RecyclerView.ViewHolder /*implements View.OnClickListener */(itemView) {
        val binding: HomeRecyclerviewBinding? = DataBindingUtil.bind(itemView)
        fun bind(railsModel: RailsModel) {
            binding?.railsModel = railsModel
        }
    }

    private inner class ViewHolderAllChannel internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val allChannels: RecyclerView = itemView as RecyclerView
    }

    private inner class ViewHolderBanner internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val banner: HomeHeroView = itemView as HomeHeroView
    }

    public override fun getNormalItemViewType(position: Int): Int {
        when {
            mList[position].sectionType.equals(ItemViewType.HERO_BANNER.name, ignoreCase = true) -> {
                mList[position].viewType = HERO_BANNER
                return HERO_BANNER
            }
            mList[position].sectionType.equals(ItemViewType.ALL_CHANNELS.name, ignoreCase = true) -> {
                mList[position].viewType = ALL_CHANNELS
                return ALL_CHANNELS
            }
            mList[position].sectionType.equals(ItemViewType.CONTINUE_WATCHING.name, ignoreCase = true) -> {
                mList[position].viewType = CONTINUE_WATCHING
                return CONTINUE_WATCHING
            }
            mList[position].sectionType.equals(ItemViewType.RAIL.name, ignoreCase = true) -> {
                mList[position].viewType = RAIL
                return RAIL
            }
            mList[position].sectionType.equals(ItemViewType.WIDGET.name, ignoreCase = true) -> {
                mList[position].viewType = WIDGET
                return WIDGET
            }
            else -> return 0
        }
    }
}
