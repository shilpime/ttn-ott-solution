package com.ottsolution.demo.ui.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ottsolution.demo.data.networking.models.HomeResponse
import com.ottsolution.demo.databinding.LayoutRailItemBinding

class RailAdapter(val list: List<HomeResponse.ContentItem>) : RecyclerView.Adapter<RailAdapter.RailItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RailItemViewHolder {
        return RailItemViewHolder(LayoutRailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RailItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class RailItemViewHolder(val binding: LayoutRailItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: HomeResponse.ContentItem) {
            binding.contentItem = model
        }
    }
}