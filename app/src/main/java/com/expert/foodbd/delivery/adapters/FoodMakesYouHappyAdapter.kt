package com.expert.foodbd.delivery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutRvFoodsMakesYouHappyBinding
import com.expert.foodbd.delivery.activity.HomeActivity.Companion.FOOD_MAKES_YOU_HAPPY_COUNT

class FoodMakesYouHappyAdapter : RecyclerView.Adapter<FoodMakesYouHappyAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutRvFoodsMakesYouHappyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = LayoutRvFoodsMakesYouHappyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return FOOD_MAKES_YOU_HAPPY_COUNT
    }

}
