package com.expert.foodbd.dining.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.R
import com.expert.foodbd.databinding.LayoutTabRestaurantBinding

class CustomTabLayoutAdapter(
    var requireActivity: Context,
    val list: ArrayList<String>,
    val click: Click
) :
    RecyclerView.Adapter<CustomTabLayoutAdapter.ViewHolder>() {
    private var rowIndex: Int = 0

    class ViewHolder(val binding: LayoutTabRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutTabRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {


        Log.i("INDEX", "POSITION OF DEFAULT INDEX $position")
        Log.i("INDEX", "POSITION OF DEFAULT ROW_INDEX $rowIndex")

        holder.binding.llMain.setOnClickListener {
            rowIndex = position
            notifyDataSetChanged()
            click.click(position)
        }


        if (rowIndex == position) {

            holder.binding.txtTitle.setTextColor(
                ContextCompat.getColor(
                    requireActivity,
                    R.color.app_color
                )
            )
            holder.binding.viewUnderLine.visibility = View.VISIBLE

        } else {
            holder.binding.txtTitle.setTextColor(
                ContextCompat.getColor(
                    requireActivity,
                    R.color.dark_grey
                )
            )
            holder.binding.viewUnderLine.visibility = View.GONE
        }

        holder.binding.txtTitle.text = list[position]


    }


    override fun getItemCount(): Int {
        return list.size
    }

    public interface Click {
        fun click(position: Int)
    }

}
