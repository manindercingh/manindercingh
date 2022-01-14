package com.expert.foodbd.dining.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.R
import com.expert.foodbd.databinding.LayoutNumberOfPeoplesBinding

class PeopleDiningAdapter(val context: Context, val list: ArrayList<String>, val people: People) :
    RecyclerView.Adapter<PeopleDiningAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutNumberOfPeoplesBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var rowIndex: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutNumberOfPeoplesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.binding.txtNumbers.text = list[position]

        holder.binding.llMain.setOnClickListener {
            rowIndex = position
            people.peoplez(holder.binding.txtNumbers.text.toString())
            notifyDataSetChanged()
        }

        if (rowIndex == position) {

            holder.binding.llMain.setBackgroundResource(R.drawable.bg_selected_layout)
            holder.binding.txtNumbers.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )


        } else {

            holder.binding.llMain.setBackgroundResource(R.drawable.bg_unselected_layout)

            holder.binding.txtNumbers.setTextColor(
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

    interface People {
        fun peoplez(people: String)
    }

}
