package com.expert.foodbd.dining.adapters

import android.content.Context
import android.widget.LinearLayout

import android.view.ViewGroup

import com.expert.foodbd.R

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast

import androidx.viewpager.widget.PagerAdapter
import java.util.*


class CustomViewPagerDiningAdapter(// Context object
    var context: Context, // Array of images
    var images: IntArray
) :
    PagerAdapter() {

    // Layout Inflater
    var mLayoutInflater: LayoutInflater
    override fun getCount(): Int {
        // return the number of images
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // inflating the item.xml
        val itemView: View =
            mLayoutInflater.inflate(R.layout.layout_image_slider_dining, container, false)

        // referencing the image view from the item.xml file
        val imageView: ImageView = itemView.findViewById(R.id.imgBanner) as ImageView

        // setting the image in the imageView
        imageView.setImageResource(images[position])

        // Adding the View
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

    // Viewpager Constructor
    init {
        images = images
        mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

}

