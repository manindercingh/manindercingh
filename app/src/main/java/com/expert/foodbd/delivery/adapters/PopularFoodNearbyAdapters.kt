package com.expert.foodbd.delivery.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.GroupModel
import com.expert.foodbd.R
import com.expert.foodbd.databinding.LayoutPopularFoodNearbyBinding
import com.expert.foodbd.delivery.activity.HomeActivity

class PopularFoodNearbyAdapters :
    RecyclerView.Adapter<PopularFoodNearbyAdapters.ViewHolder>() {
    private var listRecyclerItem: List<GroupModel>? = null

    class ViewHolder(val binding: LayoutPopularFoodNearbyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = LayoutPopularFoodNearbyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.txtDishName.text = listRecyclerItem?.get(position)!!.name
        holder.binding.txtPrice.text = listRecyclerItem!![position].price.toString()

        holder.itemView.setOnClickListener {

            HomeActivity.PRICE = listRecyclerItem!![position].price.toString()
            HomeActivity.DISH_NAME = listRecyclerItem!![position].name.toString()
            HomeActivity.TOTAL_PRICE = listRecyclerItem!![position].price.toString()
            holder.binding.root.findNavController()
                .navigate(R.id.action_homeFragment_to_dishDetailFragment)

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(viewItems: java.util.ArrayList<GroupModel>) {

        this.listRecyclerItem = viewItems
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return listRecyclerItem!!.size
    }
}
