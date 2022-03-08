package com.expert.foodbd.pro.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.R
import com.expert.foodbd.databinding.LayoutCategoryFilterBinding

class DiningCategoryAdapter(var context: Context, var list: ArrayList<String>, var onCliQ: OnCliQ) :
    RecyclerView.Adapter<DiningCategoryAdapter.ViewHolder>() {

    var rowIndex: Int? = -1

    class ViewHolder(val binding: LayoutCategoryFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            LayoutCategoryFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.root.setBackgroundResource(R.drawable.bg_button_white)

        holder.binding.root.setOnClickListener {

            rowIndex = position
            if (position == 4 || position == 6) {
                onCliQ.openSheet(position)
            } else {
                onCliQ.oncliq(position, list[position])
            }


        }

        if (position == 1) {
            holder.binding.img.visibility = View.VISIBLE
        } else if (position == 6 || position == 4) {
            holder.binding.imgDown.visibility = View.VISIBLE
        }



        if (rowIndex == position) {

            holder.binding.root.setBackgroundResource(R.drawable.bg_button)
            holder.binding.txtDishName.setTextColor(Color.WHITE)

        } else {

            holder.binding.root.setBackgroundResource(R.drawable.bg_button_white)
            holder.binding.txtDishName.setTextColor(Color.DKGRAY)

        }
        holder.binding.txtDishName.text = list[position]

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnCliQ {

        fun oncliq(position: Int, cat: String)

        fun openSheet(position: Int)
    }


}
