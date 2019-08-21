package com.ottsolution.demo.customviews;


import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ottsolution.demo.R;


import java.util.List;

public abstract class EndlessListAdapter<D extends Object, VH extends RecyclerView.ViewHolder>
        extends ListAdapter<D, VH> {
    private boolean mIsAppending = false;

    public static final int VIEW_TYPE_PROGRESS = 333;

    public EndlessListAdapter(List<D> dataList) {
        super(dataList);
    }

    public boolean isAppending() {
        return mIsAppending;
    }

    public void setIsAppending(boolean isAppending) {
        if (mIsAppending != isAppending) {
            mIsAppending = isAppending;
            try {
                if (!mIsAppending) notifyItemRemoved(getItemCount());
            } catch (Exception e) {
                Log.e("EndlessListAdapter", e.getMessage(), e);
            }
        }
    }

    @Override
    public int getNoContentVisibility() {
        return View.VISIBLE;
    }

    @Override
    public int getItemCount() {
        return isAppending() ?
                super.getItemCount() + 1 : super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return (isAppending() && position >= super.getItemCount()) ?
                VIEW_TYPE_PROGRESS : super.getItemViewType(position);
    }

    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_TYPE_PROGRESS) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_progress_bar, parent, false);
            if (!TextUtils.isEmpty(mColor)) {
                TextView loading = (TextView) v.findViewById(R.id.tv_loading);
                loading.setTextColor(Color.parseColor(mColor));
            }

            vh = new ProgressViewHolder(v);
        } else {
            vh = super.onCreateViewHolder(parent, viewType);
        }

        return (VH) vh;
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        if (holder instanceof ProgressViewHolder) {
            // do nothing
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(View v) {
            super(v);
        }
    }

    private String mColor;

    public void setLoadingColor(String color) {
        mColor = color;
    }

}

