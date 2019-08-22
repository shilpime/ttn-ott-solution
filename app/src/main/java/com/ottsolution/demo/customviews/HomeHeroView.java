package com.ottsolution.demo.customviews;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import com.bumptech.glide.Glide;
import com.ottsolution.demo.R;
import com.ottsolution.demo.data.networking.models.HomeResponse;
import com.ottsolution.demo.interfaces.CommonDTOClickListener;
import com.ottsolution.demo.utils.EventConstants;

import java.util.ArrayList;
import java.util.List;

import static com.ottsolution.demo.utils.UtilityKt.*;


public class HomeHeroView extends FrameLayout {
    private LinearLayout layout_point;
    private MyGallery gallery;
    public int len;
    private ImageView[] dots;
    int currentItem = 0;// The currently selected viewPager item
    /**
     * Time sliding
     */
    private final Handler slideHandler = new Handler();

    /**
     * Slide
     */
    private final Runnable slideRun = new Runnable() {

        @Override
        public void run() {
            currentItem++;
            currentItem = checkPosition(currentItem);
            gallery.slide(MyGallery.RIGHT);

            slideHandler.postDelayed(this, 3000);
        }
    };
    private boolean auto;

    public HomeHeroView(Context context) {
        super(context);
        init();
    }

    public HomeHeroView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeHeroView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.slide_gallery, this);
        this.layout_point = (LinearLayout) this.findViewById(R.id.layout_dots);
        this.gallery = (MyGallery) findViewById(R.id.mygallery);
//        this.gallery.setSpacing(10);

        if (isTablet(getContext())) {
            int width = getRealDisplayPoint(getContext()).x;
            this.gallery.setLayoutParams(new RelativeLayout.LayoutParams((width), (int) (width * 0.5 * 0.56)));
            this.gallery.setSpacing(20);
        } else {
//            int width = getRealDisplayPoint(getContext()).x;

//            this.gallery.setLayoutParams(new RelativeLayout.LayoutParams((width), (int) (width * 0.99 * 0.56)));

            this.gallery.setSpacing(-1);
            findViewById(R.id.space1).setVisibility(GONE);
            findViewById(R.id.space2).setVisibility(GONE);
            findViewById(R.id.space3).setVisibility(GONE);
        }
//        this.gallery.setAnimationDuration(700);

        this.gallery.setSoundEffectsEnabled(false);

        this.gallery.setListener(new GalleryListener() {
            @Override
            public void onStartSlide() {
                startSlide(false);
            }
        });


    }

    public void reset() {
        layout_point.removeAllViews();
    }

    public void initData(final HomeResponse.Items item, final CommonDTOClickListener listener) {
        auto = item.isAutoScroll();
        len = item.getContentList().size();
        dots = new ImageView[len];
        for (int j = 0; j < len; j++) {
            // Add index
            ImageView imageView = new ImageView(getContext());
//imageView.setAlpha(1);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(dpToPx(getContext(), 10), dpToPx(getContext(), 10)));
            dots[j] = imageView;
            TextView tv = new TextView(getContext());
            tv.setText("  ");
            if (j == 0) {
                // The default into the program after the first photo is selected;
                dots[j].setBackgroundResource(R.drawable.shp_white_round);
            } else {
                dots[j].setBackgroundResource(R.drawable.shp_off_white_round);
            }

            layout_point.addView(tv);
            layout_point.addView(imageView);
        }
        gallery.setAdapter(new ImageAdapter(getContext(), item.getContentList()));
        if (len > 1) gallery.setSelection(len * 100);
        gallery.setOnItemSelectedListener(selectListener);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= len) {
                    position = position % len;
                }

                View selView = parent.getSelectedView();

                ArrayList<Pair<View, String>> pairs = null;
                if (selView != null) {
                    pairs = new ArrayList<>();
                    pairs.add(Pair.create(selView.findViewById(R.id.aiv_layout_home_hero_banner), getResources().getString(R.string.profile)));
//                    pairs.add(Pair.create(selView.findViewById(R.id.iv_layout_home_hero_channel_icon), getResources().getString(R.string.rupee)));
//                    pairs.add(Pair.create(selView.findViewById(R.id.txv_layout_home_hero_live), getResources().getString(R.string.rupee)));
                }
                if (listener != null)
                    listener.onSubItemClick(pairs, item.getContentList().get(position), position, 0, EventConstants.TYPE_HERO);

            }
        });

        startSlide(false);
    }

    public class ImageAdapter extends BaseAdapter {

        private final Context context;
        private final LayoutInflater mInflater;
        private final List<HomeResponse.ContentItem> iList;
        //        private final CommonDTOClickListener listener;
        private int len;

        ImageAdapter(Context context, List<HomeResponse.ContentItem> iList) {
            this.context = context;
//            this.listener = listener;
            len = iList.size();
            mInflater = LayoutInflater.from(context);
            this.iList = iList;
        }

        public int getCount() {
            if (len > 0) {
                return Integer.MAX_VALUE;
            } else {
                return 0;
            }
        }

        public Object getItem(int position) {
            if (position >= len) {
                position = position % len;
            }

            return iList.get(position);
        }

        public long getItemId(int position) {
            if (position >= len) {
                position = position % len;
            }
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = convertView;
            if (context != null) {
                final ViewHolder holder;
                if (position >= len) {
                    position = position % len;
                }

                final HomeResponse.ContentItem item = (HomeResponse.ContentItem) getItem(position);

                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.layout_home_hero, null);
                    holder = new ViewHolder();
                    holder.liveTv = (TextView) convertView.findViewById(R.id.txv_layout_home_hero_live);
                    holder.channelIcon = (ImageView) convertView.findViewById(R.id.iv_layout_home_hero_channel_icon);
                    holder.banner = (ImageView) convertView.findViewById(R.id.aiv_layout_home_hero_banner);

                    int width = getRealDisplayPoint(context).x;
                    Log.e("BannerImage:",""+item.getImage());
                    if (isTablet(context)) {

                        holder.banner.setLayoutParams(new RelativeLayout.LayoutParams((int) (width * 0.5), (int) (width * 0.5 * 0.56)));
                    } else {

                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            width = getRealDisplayPoint(context).y;
                        }
                        holder.banner.setLayoutParams(new RelativeLayout.LayoutParams((int) (width * 0.9999) + 1, (int) (width * 0.9999 * 0.56)));

                    }

                    holder.banner.setOnTouchListener(new OnTouchListener() {

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                          iv.setAlpha(70);
                                slideHandler.removeCallbacks(slideRun);
                            }
                            return false;
                        }
                    });

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }


                String url;

                if (isTablet(context)) {
                    int width = getRealDisplayPoint(context).x;

                    url = getCloudineryUrl(item.getImage(),
                            (int) (width * 0.5), (int) (width * 0.5 * 0.56));
                } else {
                    int width;
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        width = getRealDisplayPoint(context).y;
                    } else {
                        width = getRealDisplayPoint(context).x;
                    }
                    url = getCloudineryUrl(item.getImage(),
                            width, (int) (width * 0.56));
                }
                try {
                    Glide.with(convertView.getContext()).load(url)
//                            .fitCenter()
//                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                            .placeholder(R.drawable.hero_place_holder)
                            .into(holder.banner);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                }
                try {

                    if (!TextUtils.isEmpty(item.getLogo())) {
                        holder.channelIcon.setVisibility(View.VISIBLE);
                        int width = holder.banner.getLayoutParams().height / 5;

                        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(width, width);
                        param.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.aiv_layout_home_hero_banner);
                        param.addRule(RelativeLayout.ALIGN_RIGHT, R.id.aiv_layout_home_hero_banner);
                        param.setMargins(0, 0, dpToPx(getContext(), 16), dpToPx(getContext(), 16));

                        holder.channelIcon.setLayoutParams(param);
                            Glide.with(context).load(getRoundedCloudnaryUrl(item.getLogo()
                                , width, width))
                                .into(holder.channelIcon);
                    } else {
                        holder.channelIcon.setVisibility(View.GONE);
                    }


//                    if (!TextUtils.isEmpty(item.logo)) {
//                        holder.channelIcon.setVisibility(View.VISIBLE);
//                        Glide.with(context).load(getRoundedCloudnaryUrl(item.logo
//                                , dpToPx(context, 55), dpToPx(context, 55)))
//                                .into(holder.channelIcon);
//                    } else {
//                        holder.channelIcon.setVisibility(View.GONE);
//                    }
                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                }

                if (item.getContentType().equalsIgnoreCase("LIVE_CHANNEL") ||
                        item.getContentType().equalsIgnoreCase("CUSTOM_LIVE_DETAIL")) {
                    holder.liveTv.setVisibility(View.VISIBLE);

                } else {
                    ViewCompat.setElevation(holder.liveTv, dpToPx(context, 10));
                    holder.liveTv.setVisibility(View.GONE);
                }

            }
            return convertView;
        }

        class ViewHolder {
            private ImageView banner;
            private TextView liveTv;
            private ImageView channelIcon;
        }
    }

    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            if (position >= len) {
                position = position % len;
                currentItem = position;
            } else {
                currentItem = position;
            }
            selectPage();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };


    /**
     * Start sliding
     */
    public void startSlide(boolean frmZero) {
        if (auto && len > 1) {
            if (frmZero) gallery.setSelection(len * 100);
            slideHandler.postDelayed(slideRun, 3000);
        }
    }

    public void stopSlide() {
        if (slideHandler != null) {
            slideHandler.removeCallbacksAndMessages(null);
        }
    }

    public int checkPosition(int position) {
        if (position >= len) {
            position = position % len;
        }

        return position;
    }

    /**
     * Sets the currently selected pages
     */
    private void selectPage() {
        /** Set the currently displayed page */
        for (int i = 0; i < len; i++) {
            dots[currentItem].setBackgroundResource(R.drawable.shp_white_round);
            if (currentItem != i) {
                dots[i].setBackgroundResource(R.drawable.shp_off_white_round);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (slideHandler != null) slideHandler.removeCallbacks(slideRun);

    }
}
