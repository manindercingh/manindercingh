package com.expert.foodbd.delivery.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expert.foodbd.databinding.LayoutSelectAddressBinding

class SelectAddressListAdapters : RecyclerView.Adapter<SelectAddressListAdapters.ViewHolder>() {

    var radioButton: Boolean = false
    var radioButton2: Boolean = false

    class ViewHolder(val binding: LayoutSelectAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutSelectAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position == 1) {
            holder.binding.mrdButton.text = "Office"
            holder.binding.txtAddress.text = "Barrie,Canada"
            holder.binding.txtPhoneNum.text = "+1-408-987-7117"
        }



        holder.itemView.setOnClickListener {

            Log.i("radio","CHECK RADIO $radioButton")



//            if (radioButton) {
//
//                radioButton = false
//
//                Log.i("radio","CHECK RADIO turu $radioButton")
//                holder.binding.mrdButton.isChecked = false
//
//            } else {
//                Log.i("radio","CHECK RADIO falsee $radioButton")
//                holder.binding.mrdButton.isChecked = true
//                radioButton=true
//            }

        }


    }

    override fun getItemCount(): Int {
        return 2
    }
}