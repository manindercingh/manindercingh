package com.expert.foodbd.pro.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutBestOffersAroundYouBinding

class BestOffersAroundYouAdapter(val click: Click, val context: Context) :
    RecyclerView.Adapter<BestOffersAroundYouAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutBestOffersAroundYouBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutBestOffersAroundYouBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.rlItem.setOnClickListener { click.getClick(position) }

    }

    public interface Click {
        fun getClick(position: Int)
    }

    override fun getItemCount(): Int {
        return 10
    }

}

