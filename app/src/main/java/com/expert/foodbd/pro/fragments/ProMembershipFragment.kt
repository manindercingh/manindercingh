package com.expert.foodbd.pro.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentProMembershipBinding
import com.expert.foodbd.databinding.LayoutBottomSheetCuisinesBinding
import com.expert.foodbd.databinding.LayoutBottomSheetMoreFiltersBinding
import com.expert.foodbd.databinding.LayoutBottomSheetProMembershipBinding
import com.expert.foodbd.delivery.adapters.TopBrandsAdapter
import com.expert.foodbd.pro.adapters.*
import com.google.android.material.bottomsheet.BottomSheetDialog


class ProMembershipFragment : Fragment(), BestOffersAroundYouAdapter.Click,
    DiningCategoryAdapter.OnCliQ, ProRestaurantsDiningAdapter.ProClick {
    private var _binding: FragmentProMembershipBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProMembershipBinding.inflate(layoutInflater, container, false)


        binding.llDelivery.visibility = View.VISIBLE
        binding.llDining.visibility = View.GONE

        setClicks()

        setAdapters()

        return binding.root

    }

    private fun openBottomSheet() {

        val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val sheetBinding =
            LayoutBottomSheetProMembershipBinding.inflate(LayoutInflater.from(requireContext()))
        bottomSheet.setContentView(sheetBinding.root)
        bottomSheet.show()
        sheetBinding.btnAddPaymentMethod.setOnClickListener {

            binding.root.findNavController()
                .navigate(R.id.action_proMembershipFragment_to_addPaymentMethodFragment)
            bottomSheet.dismiss()

        }

    }

    private fun setClicks() {

        binding.txtDelivery.setOnClickListener {

            binding.txtDelivery.setBackgroundResource(R.drawable.bg_button_square_black)
            binding.txtDelivery.setTextColor(Color.WHITE)
            binding.txtDining.setBackgroundResource(android.R.color.transparent)
            binding.txtDining.setTextColor(Color.BLACK)
            binding.llDelivery.visibility = View.VISIBLE
            binding.llDining.visibility = View.GONE

        }

        binding.txtDining.setOnClickListener {

            binding.txtDining.setBackgroundResource(R.drawable.bg_button_square_black)
            binding.txtDining.setTextColor(Color.WHITE)

            binding.txtDelivery.setBackgroundResource(android.R.color.transparent)
            binding.txtDelivery.setTextColor(Color.BLACK)

            binding.llDelivery.visibility = View.GONE
            binding.llDining.visibility = View.VISIBLE

        }

        binding.txtUnlockProBenefit.setOnClickListener { openBottomSheet() }


    }

    private fun setAdapters() {

//        Delivery Section

        val adapters = ProExploreAdapters()
        binding.rvProExplore.adapter = adapters

        val dealsAdapters = BiggestBaddestDealsAdapter()
        binding.rvBiggestDeals.adapter = dealsAdapters

        val topBrandsAdapter = TopBrandsAdapter()
        binding.rvTopBrands.adapter = topBrandsAdapter

        val offersAdapter = BestOffersAroundYouAdapter(this, requireContext())
        binding.rvBestOffersAroundYou.adapter = offersAdapter


//        Dining Section

        val dataList = arrayListOf<String>()
        val proList = arrayListOf<String>()

        dataList.clear()
        dataList.add("Filters")
        dataList.add("Pro Offers")
        dataList.add("Open Now")
        dataList.add("Serves Alcohol")
        dataList.add("Rating")
        dataList.add("Cost")

        val diningCategoryAdapter = DiningCategoryAdapter(requireContext(), list = dataList, this)
        binding.rvCategoryFilter.adapter = diningCategoryAdapter

        proList.clear()
        proList.add("10")
        proList.add("20")
        proList.add("15")
        proList.add("10")
        proList.add("15")
        proList.add("20")
        proList.add("25")
        proList.add("20")
        proList.add("10")

        val proDiningAdapter = ProRestaurantsDiningAdapter(this, requireContext(), list = proList)
        binding.rvProDining.adapter = proDiningAdapter

    }

    override fun getClick(position: Int) {

        Toast.makeText(requireContext(), "position : $position", Toast.LENGTH_SHORT).show()

    }

    override fun oncliq(position: Int, cat: String) {
        showCuisines()
    }

    override fun openSheet(position: Int) {

        if (position == 6) {
            showMoreFilters()
        } else {
            showCuisines()
        }

    }

    private fun showMoreFilters() {
        val navMain = LayoutBottomSheetMoreFiltersBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
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
        arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, users)
        navMain.listPopularCuisines.adapter = arrayAdapter
        bottomSheetDialog.show()

    }

    override fun getProClick(position: Int) {


    }

}