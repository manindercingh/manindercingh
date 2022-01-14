package com.expert.foodbd

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity

class PopularCuisinesAdapter(
    context: Context,
    arrayOfUsers: ArrayList<PopularCuisinesModel>
) : BaseAdapter() {


    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): PopularCuisinesModel {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }


    override fun getView(position: Int, convertView: View, parent: ViewGroup?): View {
        val popularCusion: PopularCuisinesModel = getItem(position)
//        convertView = LayoutInflater.from(parent?.context).inflate(R.layout.layout_list_view_popular_cuisines, parent, false)

        var checkBox = convertView.findViewById<CheckBox>(R.id.checkbox_Popular)

        checkBox.text = popularCusion.name

        return convertView

    }
}