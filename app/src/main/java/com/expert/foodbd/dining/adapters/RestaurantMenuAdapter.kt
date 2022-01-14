package com.expert.foodbd.dining.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutRestaurantFoodMenuBinding

class RestaurantMenuAdapter : RecyclerView.Adapter<RestaurantMenuAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutRestaurantFoodMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutRestaurantFoodMenuBinding.inflate(
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
