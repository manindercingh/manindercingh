package com.expert.foodbd.delivery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutNearbyRestaurantInActivityBinding


class RestaurantNearbyActivityAdapters :
    RecyclerView.Adapter<RestaurantNearbyActivityAdapters.ViewHolder>() {
    class ViewHolder(val binding: LayoutNearbyRestaurantInActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = LayoutNearbyRestaurantInActivityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 5
    }
}
