package com.expert.foodbd

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.expert.foodbd.databinding.FragmentAddPaymentMethodBinding

class AddPaymentMethodFragment : Fragment() {
    private var _binding: FragmentAddPaymentMethodBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddPaymentMethodBinding.inflate(layoutInflater, container, false)

        setClicks()

        return binding.root

    }

    private fun setClicks() {

        binding.rlCard.setOnClickListener { Log.i("TAG", "CLICKED") }

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

    }

}