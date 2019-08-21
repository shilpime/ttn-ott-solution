package com.ottsolution.demo.customviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ottsolution.demo.R;


import java.util.List;

public abstract class ListAdapter<D, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    protected List<D> mDataList;

    protected static final int VIEW_TYPE_NORMAL = 0;
    public static final int VIEW_TYPE_EMPTY = 111;

    public ListAdapter(List<D> dataList) {
        mDataList = dataList;
    }

    @Override
    public int getItemCount() {
        return mDataList.isEmpty() ? 1 : mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList.isEmpty()) {
            return VIEW_TYPE_EMPTY;
        } else {
            return getNormalItemViewType(position);
        }
    }

    public void clear() {
        int size = mDataList.size();
        mDataList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<D> data) {
        mDataList.addAll(data);
        notifyItemInserted(mDataList.size());
    }

    protected D getItemAt(int position) {
        return mDataList.get(position);
    }

    protected D replaceItemAt(int position, D newItem) {
        D item = mDataList.set(position, newItem);
        notifyItemChanged(position);
        return item;
    }

    protected void addItem(int index, D newItem) {
        mDataList.add(index, newItem);
        notifyItemInserted(index);
    }

    protected void addItem(D newItem) {
        mDataList.add(newItem);
        notifyItemInserted(mDataList.size() - 1);
    }

    protected D removeItem(int index) {
        if (mDataList.size() > 0) {
            D item = mDataList.remove(index);
            notifyItemRemoved(index);

            return item;
        }

        return null;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_TYPE_EMPTY) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_empty_view, parent, false);
            TextView noContent = (TextView) v.findViewById(R.id.txv_frag_livetv_error);
noContent.setVisibility(getNoContentVisibility());
            vh = new EmptyViewHolder(v);
        } else {
            vh = createNormalViewHolder(parent, viewType);
        }

        return (VH) vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (holder instanceof EmptyViewHolder) {
            // do nothing
        } else {
            // Prevent binding of child view holder when List is NULL/Empty
            if (mDataList != null && !mDataList.isEmpty()) {
                bindNormalViewHolder(holder, position);
            }
        }
    }

    protected abstract VH createNormalViewHolder(ViewGroup parent, int viewType);

    protected abstract void bindNormalViewHolder(VH holder, int position);

    protected int getNormalItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }

    public int getNoContentVisibility() {
        return View.VISIBLE;
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View v) {
            super(v);
        }
    }
}

