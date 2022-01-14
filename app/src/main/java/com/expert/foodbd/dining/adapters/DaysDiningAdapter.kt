package com.expert.foodbd.dining.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.R
import com.expert.foodbd.databinding.LayoutItemDaysBinding

class DaysDiningAdapter(val requireActivity: Context, val days: Days) :
    RecyclerView.Adapter<DaysDiningAdapter.ViewHolder>() {

    private var rowIndex: Int = -1

    class ViewHolder(val binding: LayoutItemDaysBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutItemDaysBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.binding.llMain.setOnClickListener {
            rowIndex = position
            days.days(holder.binding.txtDate.text.toString())
            notifyDataSetChanged()
        }

        if (rowIndex == position) {

            holder.binding.llMain.setBackgroundResource(R.drawable.bg_selected_layout)
            holder.binding.txtDate.setTextColor(
                ContextCompat.getColor(
                    requireActivity,
                    R.color.white
                )
            )

            holder.binding.txtDay.setTextColor(
                ContextCompat.getColor(
                    requireActivity,
                    R.color.white
                )
            )

        } else {

            holder.binding.llMain.setBackgroundResource(R.drawable.bg_unselected_layout)

            holder.binding.txtDate.setTextColor(
                ContextCompat.getColor(
                    requireActivity,
                    R.color.dark_grey
                )
            )

            holder.binding.txtDay.setTextColor(
                ContextCompat.getColor(
                    requireActivity,
                    R.color.dark_grey
                )
            )

        }

    }

    override fun getItemCount(): Int {
        return 5
    }

    public interface Days {
        fun days(days: String)
    }

}
