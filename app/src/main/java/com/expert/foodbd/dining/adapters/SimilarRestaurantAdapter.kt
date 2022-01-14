package com.expert.foodbd.dining.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutSimilarRestaurantBinding

class SimilarRestaurantAdapter : RecyclerView.Adapter<SimilarRestaurantAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutSimilarRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutSimilarRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }

}
