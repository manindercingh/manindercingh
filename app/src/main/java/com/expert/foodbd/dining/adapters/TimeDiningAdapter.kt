package com.expert.foodbd.dining.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.R
import com.expert.foodbd.databinding.LayoutTimeBinding

class TimeDiningAdapter(val context: Context, val list: ArrayList<String>,val time: Time) :
    RecyclerView.Adapter<TimeDiningAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutTimeBinding) : RecyclerView.ViewHolder(binding.root)

    private var rowIndex: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutTimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.binding.txtTime.text = list[position]

        holder.binding.llMain.setOnClickListener {
            rowIndex = position
            time.time(list[position])
            notifyDataSetChanged()
        }

        if (rowIndex == position) {

            holder.binding.llMain.setBackgroundResource(R.drawable.bg_selected_layout)
            holder.binding.txtTime.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )


        } else {

            holder.binding.llMain.setBackgroundResource(R.drawable.bg_unselected_layout)

            holder.binding.txtTime.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.dark_grey
                )
            )

        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface Time {
        fun time(time: String)
    }
}
