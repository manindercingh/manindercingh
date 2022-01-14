package com.expert.foodbd.dining.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutReviewTagsBinding

class ReviewTagsAdapter(context: Context, var list: ArrayList<String>) :
    RecyclerView.Adapter<ReviewTagsAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutReviewTagsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutReviewTagsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtTitle.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
