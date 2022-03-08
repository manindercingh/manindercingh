package com.expert.foodbd.delivery.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentAddItemBinding
import com.expert.foodbd.delivery.activity.HomeActivity


class AddItemFragment : Fragment() {
    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!
    private var intTaxes: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemBinding.inflate(layoutInflater, container, false)

        intTaxes = 20
        getData()
        setClicks()

        return binding.root
    }

    private fun setClicks() {

        binding.btnAddAddress.setOnClickListener {
            binding.root.findNavController()
                .navigate(R.id.action_addItemFragment_to_selectAddressFragment)
        }

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

    }

    @SuppressLint("SetTextI18n")
    private fun getData() {


        binding.txtDishName.text = HomeActivity.DISH_NAME
        binding.txtTotal.text = "$ ${HomeActivity.TOTAL_PRICE}"
        binding.txtTaxesAndCharges.text = "$ $intTaxes"


    }

}