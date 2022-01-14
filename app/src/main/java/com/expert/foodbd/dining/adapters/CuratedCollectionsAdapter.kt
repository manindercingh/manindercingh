package com.expert.foodbd.dining.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutCuratedCollectionsBinding

class CuratedCollectionsAdapter : RecyclerView.Adapter<CuratedCollectionsAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutCuratedCollectionsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = LayoutCuratedCollectionsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 10
    }

}
