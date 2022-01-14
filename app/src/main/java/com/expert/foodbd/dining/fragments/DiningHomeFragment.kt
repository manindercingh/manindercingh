package com.expert.foodbd.dining.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentDiningHomeBinding
import com.expert.foodbd.databinding.LayoutBottomSheetCuisinesBinding
import com.expert.foodbd.databinding.LayoutBottomSheetMoreFiltersBinding
import com.expert.foodbd.delivery.adapters.CategoryFilterAdapter
import com.expert.foodbd.dining.adapters.CustomViewPagerDiningAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

import android.widget.LinearLayout
import androidx.navigation.findNavController
import com.expert.foodbd.delivery.activity.HomeActivity
import com.expert.foodbd.delivery.activity.HomeActivity.Companion.FOOD_MAKES_YOU_HAPPY_COUNT
import com.expert.foodbd.dining.adapters.CuratedCollectionsAdapter
import com.expert.foodbd.dining.adapters.DiningGridAdapter
import com.expert.foodbd.dining.adapters.PopularRestaurantsAdapter


class DiningHomeFragment : Fragment(), CategoryFilterAdapter.OnCliQ,
    PopularRestaurantsAdapter.Click {
    private var _binding: FragmentDiningHomeBinding? = null
    private val binding get() = _binding!!
    private var currentPage: Int = 0
    private var timer: Timer? = null
    private var dotsCounts = 0
    private lateinit var dots: Array<ImageView?>

    private val DELAY_MS: Long = 500
    private val gridAdapter = DiningGridAdapter()
    private val curatedAdapters = CuratedCollectionsAdapter()
    private val PERIOD_MS: Long = 1500


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiningHomeBinding.inflate(layoutInflater, container, false)
        FOOD_MAKES_YOU_HAPPY_COUNT = 8

        setAdapters()
        setViewPager()
        setClicks()
        return binding.root

    }

    private fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0F
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setClicks() {

        binding.llSeeLessMore.setOnClickListener {
            slideUp(binding.llSeeLessMore)
            gridAdapter.notifyDataSetChanged()
            if (FOOD_MAKES_YOU_HAPPY_COUNT == 8) {
                FOOD_MAKES_YOU_HAPPY_COUNT = 16
            } else {
                FOOD_MAKES_YOU_HAPPY_COUNT = 8
            }

        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
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
        }, DELAY_MS, PERIOD_MS)


        dotsCounts = customViewPagerDiningAdapter.count
        dots = arrayOfNulls<ImageView>(dotsCounts)
        for (i in 0 until dotsCounts) {
            dots[i] = ImageView(requireContext())
            dots[i]!!.setImageDrawable(resources.getDrawable(R.drawable.inactive_dots))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(4, 0, 4, 0)
            binding.dotsContainer.addView(dots[i], params)
        }
        dots[0]!!.setImageDrawable(resources.getDrawable(R.drawable.active_dots))

    }


    private fun setAdapters() {
        val dataList = arrayListOf<String>()

        dataList.clear()

        dataList.add("Pro")
        dataList.add("Nearest")
        dataList.add("Rating 4.0+")
        dataList.add("Book Table")
        dataList.add("Cuisines")
        dataList.add("New Arrivals")
        dataList.add("More")

        val categoryAdapter = CategoryFilterAdapter(requireContext(), list = dataList, this)
        binding.rvCategoryFilter.adapter = categoryAdapter

        binding.rvDiningGrid.adapter = gridAdapter
        binding.rvCuratedCollections.adapter = curatedAdapters


        val restaurantAdapter = PopularRestaurantsAdapter(this, requireContext())
        binding.rvPopularRestaurants.adapter = restaurantAdapter
    }

    override fun oncliq(position: Int, cat: String) {
        if (position == 6) {
            showMoreFilters()
        } else {
            showCuisines()
        }

    }


    private fun showMoreFilters() {
        val navMain = LayoutBottomSheetMoreFiltersBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
//        bottomSheetDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.setContentView(navMain.root)
        bottomSheetDialog.show()
        navMain.txtClearAll.setOnClickListener {
            bottomSheetDialog.dismiss()
            Toast.makeText(requireContext(), "Cleared", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showCuisines() {

        val navMain = LayoutBottomSheetCuisinesBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(navMain.root)

        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
            "List 1",
            "List 2",
            "List 3",
            "List 4",
            "List 5"
        )
        ;
        arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, users)
        navMain.listPopularCuisines.adapter = arrayAdapter
        bottomSheetDialog.show()

    }


    override fun openSheet(position: Int) {
        if (position == 6) {
            showMoreFilters()
        } else {
            showCuisines()
        }
    }

    override fun getClick(position: Int) {

        binding.root.findNavController()
            .navigate(R.id.action_diningHomeFragment_to_popularRestaurantDetailsFragment)
//        Toast.makeText(requireContext(), position, Toast.LENGTH_SHORT).show()

    }

}