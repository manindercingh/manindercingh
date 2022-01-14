package com.expert.foodbd.delivery.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.expert.foodbd.databinding.ActivityViewAllListBinding
import com.expert.foodbd.delivery.adapters.RestaurantNearbyActivityAdapters

class ViewAllListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewAllListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllListBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setData()
//        setClicks()

    }

    private fun setClicks() {

        binding.imgBack.setOnClickListener { onBackPressed() }

    }


    private fun setData() {

        val check: String = intent.getStringExtra("type").toString()

//        val adapters = PopularFoodNearbyAdaptersInFragment(this)
//        binding.rvPopularFoodsNearby.adapter = adapters

        val ad = RestaurantNearbyActivityAdapters()
        binding.rvNearbyRestaurants.adapter = ad

        if (check == "txtPopularFN") {

            binding.txtTitle.text = "Popular Food Nearby"
            binding.rvPopularFoodsNearby.visibility = View.VISIBLE
            binding.rvNearbyRestaurants.visibility = View.INVISIBLE

        } else if (check == "txtRestaurantNearby") {

            binding.txtTitle.text = "Nearby Restaurants"
            binding.rvPopularFoodsNearby.visibility = View.INVISIBLE
            binding.rvNearbyRestaurants.visibility = View.VISIBLE

        }

    }
}