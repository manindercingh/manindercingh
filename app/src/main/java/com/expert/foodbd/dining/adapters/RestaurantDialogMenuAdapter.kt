package com.expert.foodbd.dining.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutDialogMenuItemsBinding

class RestaurantDialogMenuAdapter(
    var requireActivity: Context,
    val list: ArrayList<String>,
    val click: Click
) :
    RecyclerView.Adapter<RestaurantDialogMenuAdapter.ViewHolder>() {


    class ViewHolder(val binding: LayoutDialogMenuItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutDialogMenuItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.binding.txtTitle.text = list[position]

    }


    override fun getItemCount(): Int {
        return list.size
    }

    public interface Click {
        fun menuClick(position: Int)
    }

}



