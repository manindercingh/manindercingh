package com.expert.foodbd.pro.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutProDiningBinding

class ProRestaurantsDiningAdapter(
    val click: ProClick,
    val context: Context,
    val list: ArrayList<String>
) :
    RecyclerView.Adapter<ProRestaurantsDiningAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutProDiningBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutProDiningBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.rlItem.setOnClickListener { click.getProClick(position) }

        holder.binding.txtOffers.text = "${list[position]}% Off with Pro"

    }

    interface ProClick {
        fun getProClick(position: Int)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

