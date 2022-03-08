package com.expert.foodbd.delivery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutRvTopBrandsBinding

class TopBrandsAdapter : RecyclerView.Adapter<TopBrandsAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutRvTopBrandsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = LayoutRvTopBrandsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 8
    }
}

