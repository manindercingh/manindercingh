package com.expert.foodbd.delivery.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.delivery.models.GroupModel
import com.expert.foodbd.databinding.LayoutSelectExtraOptionsBinding
import java.util.*

class ChooseYourBeveragesAdapter : RecyclerView.Adapter<ChooseYourBeveragesAdapter.ViewHolder>() {

    private var list: List<GroupModel>? = null

    class ViewHolder(val binding: LayoutSelectExtraOptionsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = LayoutSelectExtraOptionsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.txtOptionName.text = list!![position].name
        holder.binding.checkBox.text = "$ ${list!![position]?.price}"

        holder.itemView.setOnClickListener {

            holder.binding.checkBox.isChecked = !holder.binding.checkBox.isChecked

        }


    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    fun setData(chooseYourBeverages: ArrayList<GroupModel>) {

        list = chooseYourBeverages

    }
}