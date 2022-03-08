package com.expert.foodbd.dining.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentPopularRestaurantDetailsBinding
import com.expert.foodbd.dining.adapters.*
import java.util.*


class PopularRestaurantDetailsFragment : Fragment() {
    private var _binding: FragmentPopularRestaurantDetailsBinding? = null
    private var currentPage: Int = 0
    private var timer: Timer? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPopularRestaurantDetailsBinding.inflate(layoutInflater, container, false)

        setClicks()

        setViewPager()

        setAdapters()

        return binding.root

    }

    private fun setClicks() {

        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.rlFoodDelivery.setOnClickListener {

            binding.root.findNavController()
                .navigate(R.id.action_popularRestaurantDetailsFragment_to_foodDeliveryDiningFragment)

        }

        binding.rlBookTable.setOnClickListener {

            binding.root.findNavController()
                .navigate(R.id.action_popularRestaurantDetailsFragment_to_bookTableFragment)

        }

    }

    private fun setAdapters() {

        val menuAdapter = RestaurantMenuAdapter()
        binding.rvRestaurantFoodMenu.adapter = menuAdapter
        val titles = arrayListOf<String>()
        titles.clear()
        titles.add("Peaceful Ambience")
        titles.add("Comfortable Seating Area")
        titles.add("Economical Price")
        titles.add("Hospitality")
        titles.add("Tasty Food")
        titles.add("Good Place")
        titles.add("Qualified Staff")

        val reviewTagsAdapter = ReviewTagsAdapter(requireContext(), list = titles)
        binding.rvReviewTags.adapter = reviewTagsAdapter

        val recentReviewsAdapter = RecentReviewsAdapter(requireContext(), list = titles)
        binding.rvReview.adapter = recentReviewsAdapter

        val similarRestaurantAdapter = SimilarRestaurantAdapter()
        binding.rvSimilarRestaurant.adapter = similarRestaurantAdapter

    }

    private fun setViewPager() {

        val images = intArrayOf(
            R.drawable.img_restaurant_banners2,
            R.drawable.img_restaurant_banners,
            R.drawable.img_restaurant_banners2,
            R.drawable.img_restaurant_banners
        )

        val customViewPagerDiningAdapter = CustomViewPagerDiningAdapter(requireContext(), images)

        binding.viewPager.adapter = customViewPagerDiningAdapter

        binding.viewPager.scrollIndicators

        val handler = Handler()

        val update = Runnable {
            if (currentPage == images.size - 1) {
                currentPage = 0
            }
            binding.viewPager.setCurrentItem(currentPage++, true)
        }

        timer = Timer()

        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 1000, 1500)

    }

}