package com.ottsolution.demo.ui.features.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ottsolution.demo.R;
import com.ottsolution.demo.customviews.EndlessListAdapter;
import com.ottsolution.demo.data.networking.models.HomeResponse;
import com.ottsolution.demo.databinding.HomeRecyclerviewBinding;

import java.util.List;


public class HomeAdapter extends EndlessListAdapter<HomeResponse.Items, RecyclerView.ViewHolder> {

    private static final int HERO_BANNER = 1000, ALL_CHANNELS = 2000, RAIL = 3000, CONTINUE_WATCHING = 4000, WIDGET = 5000;
    private final LayoutInflater inflater;
//    private final HorizontalSpaceItemDecoration decoration;
//    private final CommonDTOClickListener mBannerClick;

    private List<HomeResponse.Items> mList;
//    private RecyclerViewClickItem mSeeAllListener;
    private RecyclerView.RecycledViewPool mSharedPoolPot = new RecyclerView.RecycledViewPool();
    private RecyclerView.RecycledViewPool mSharedPoolLan = new RecyclerView.RecycledViewPool();
    private Activity mActivity;
    boolean reset;

//    private CommonDTOClickListener mItemClickedListener;
    private ViewHolderBanner mBanner;
//    private VODShowListAdapter continueWatchingAdapter;


    public HomeAdapter(List<HomeResponse.Items> iList, Activity iActivity) {
        super(iList);
        mList = iList;
//        mSeeAllListener = iSeeAllListener;
//        mBannerClick = listener;
//        mItemClickedListener = iItemClickedListener;
        mActivity = iActivity;
        inflater = LayoutInflater.from(iActivity);
//        decoration = new HorizontalSpaceItemDecoration(Utility.dpToPx(iActivity, 2));
    }

    @Override
    public int getNoContentVisibility() {
        return View.GONE;
    }
/*

    public ChannelItemClickListener getListener() {
        return mListener;
    }

    public void setListener(ChannelItemClickListener listener) {
        mListener = listener;
    }

    private ChannelItemClickListener mListener;
*/


    @Override
    protected RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent, int viewType) {

        View view;

        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {

            case HERO_BANNER:

//                view = new HomeHeroView(parent.getContext());
//                viewHolder = mBanner = new ViewHolderBanner(view);
//                parent.setTag(null);
                break;

            case ALL_CHANNELS:

                RecyclerView allChannel = new RecyclerView(parent.getContext());
                allChannel.setLayoutManager(new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false));
//                allChannel.addItemDecoration(decoration);
                view = allChannel;
                parent.setTag(null);
                viewHolder = new ViewHolderAllChannel(view);
                break;

            case RAIL:
                view = inflater.inflate(R.layout.home_recyclerview, parent, false);
                viewHolder = new ViewHolder(view);
                break;
            case CONTINUE_WATCHING:
                view = inflater.inflate(R.layout.home_recyclerview, parent, false);
                viewHolder = new ViewHolder(view);
                break;
            case WIDGET:
                view = inflater.inflate(R.layout.home_recyclerview, parent, false);
                viewHolder = new ViewHolder(view);
                break;

        }


        return viewHolder;
    }

    @Override
    public void clear() {
        super.clear();
        reset = true;
    }

    @Override
    protected void bindNormalViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final HomeResponse.Items item;
        switch (holder.getItemViewType()) {

            case HERO_BANNER:
//                item = mList.get(position);
//                ViewHolderBanner bannerViewHolder = (ViewHolderBanner) holder;
//                if (bannerViewHolder.banner.len == 0)
//                    bannerViewHolder.banner.initData(item, mBannerClick);
//                else if (reset) {
//                    bannerViewHolder.banner.reset();
//                    bannerViewHolder.banner.initData(item, mBannerClick);
//                    reset = false;
//                } else
//                    bannerViewHolder.banner.startSlide(true);
                break;

            case ALL_CHANNELS:
//                item = mList.get(position);
//                ViewHolderAllChannel allChannelViewHolder = (ViewHolderAllChannel) holder;
//
//                if (allChannelViewHolder.allChannels.getAdapter() == null)
//                    allChannelViewHolder.allChannels.setAdapter(new AllChannelLiveTVAdapter(mActivity, item.getCommonDTO(), mListener, position, item.getTitle()));
                break;

            case RAIL:
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
                break;
            case CONTINUE_WATCHING:
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
//
////                        mSeeAllListener.onItemClicked(position, item);
//
//                    }
//                });
//
//
//                continueWatchingAdapter = new VODShowListAdapter(item.getCommonDTO(), mode, mItemClickedListener, mActivity, position, item.getTitle(), OnDemandTvShowsChildFragment.TYPE_TV_SHOWS, item.getSectionType());
//
//                viewHolder.mBinding.homeRecyclerView.setAdapter(continueWatchingAdapter);
//                SharedPreference.setInt(TataSkyApp.getContext(), AppConstants.PREF_KEY_CONTINUE_WATCHING_POSITION, position);
////                if (position == 1) {
////                    TapTargetUtil.TargetData targetData = new TapTargetUtil.TargetData();
////                    targetData.title = "Tap to See all contents";
////                    targetData.setView(viewHolder.mBinding.homeSeeAll);
////                    targetData.setTintTarget(false);
////                    TapTargetUtil.Companion.highlightView(mActivity, targetData);
////                }

                break;
            case WIDGET:
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
                break;
        }
    }

    public void updateHeroSlideBehaviour(boolean pauseSlide) {
//        if (mBanner != null && mBanner.banner != null) {
//            if (pauseSlide) {
//                mBanner.banner.stopSlide();
//            } else {
//                mBanner.banner.startSlide(false);
//            }
//        }
    }

    public void notifyItemForPosition(HomeResponse.Items item) {
//        if (continueWatchingAdapter != null) {
//            continueWatchingAdapter.refresh(item.getCommonDTO());
//        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener */ {
        private HomeRecyclerviewBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
//            HorizontalSpaceItemDecoration decoration = new HorizontalSpaceItemDecoration(Utility.dpToPx(itemView.getContext(), 15));
//            mBinding.homeRecyclerView.addItemDecoration(decoration);
        }

        public HomeRecyclerviewBinding getBinding() {
            return mBinding;
        }
    }

    private class ViewHolderAllChannel extends RecyclerView.ViewHolder {
        private RecyclerView allChannels;

        ViewHolderAllChannel(View itemView) {
            super(itemView);

            allChannels = (RecyclerView) itemView;
        }
    }

    private class ViewHolderBanner extends RecyclerView.ViewHolder {
//        private HomeHeroView banner;

        ViewHolderBanner(View itemView) {
            super(itemView);

//            banner = (HomeHeroView) itemView;
        }

    }


    @Override
    public int getNormalItemViewType(int position) {

        if (mList.get(position).getSectionType().equalsIgnoreCase(mActivity.getString(R.string.hero_banner))) {
            mList.get(position).setViewType(HERO_BANNER);
            return HERO_BANNER;
        } else if (mList.get(position).getSectionType().equalsIgnoreCase(mActivity.getString(R.string.all_channels))) {
            mList.get(position).setViewType(ALL_CHANNELS);
            return ALL_CHANNELS;
        } else if (mList.get(position).getSectionType().equalsIgnoreCase(mActivity.getString(R.string.continue_watch))) {
            mList.get(position).setViewType(CONTINUE_WATCHING);
            return CONTINUE_WATCHING;
        } else if (mList.get(position).getSectionType().equalsIgnoreCase(mActivity.getString(R.string.rail))) {
            mList.get(position).setViewType(RAIL);
            return RAIL;
        } else if (mList.get(position).getSectionType().equalsIgnoreCase("WIDGET")) {
            mList.get(position).setViewType(WIDGET);
            return WIDGET;
        }
        return 0;
    }

}
