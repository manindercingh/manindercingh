package com.expert.foodbd.delivery.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.GroupModel
import com.expert.foodbd.databinding.LayoutSelectExtraOptionsBinding
import java.util.*


class ChooseYourSideAdapter(private val sideClick: SideClick) :
    RecyclerView.Adapter<ChooseYourSideAdapter.ViewHolder>() {

    interface SideClick {

        fun sideClick(price: Int)

    }

    private var list: List<GroupModel>? = null
    private val listIds = ArrayList<String>()

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

        listIds.add("")

        Log.i("info", "List Info $listIds")

        holder.binding.txtOptionName.text = list!![position].name
        holder.binding.checkBox.text = "$ ${list!![position].price}"

        holder.itemView.setOnClickListener {

            if (holder.binding.checkBox.isChecked) {

                holder.binding.checkBox.isChecked = false

                sideClick.sideClick(list!![position].price!!)

                listIds[position] = "nullId"

                Log.i("info", "List Info false $listIds")

            } else {

                holder.binding.checkBox.isChecked = true
                listIds[position] = list!![position].id.toString()
                Log.i("info", "List Info true $listIds")

            }

        }

    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    fun setData(viewItems: ArrayList<GroupModel>) {

        list = viewItems

    }
}