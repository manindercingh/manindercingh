package com.expert.foodbd.delivery.fragments

//import com.razorpay.Checkout
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.expert.foodbd.databinding.FragmentSelectAddressBinding

import android.graphics.drawable.ColorDrawable

import android.app.Dialog
import android.graphics.Color
import android.view.*
import com.expert.foodbd.databinding.LayoutDialogPaymentMethodBinding
import android.view.LayoutInflater
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.google.android.material.bottomsheet.BottomSheetDialog


class SelectAddressFragment : Fragment() {
    private var _binding: FragmentSelectAddressBinding? = null
    private val binding get() = _binding!!
    private var mrdHome = false
    private var mrdOffice = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectAddressBinding.inflate(layoutInflater, container, false)

        setData()
        setClicks()

        return binding.root

    }

    private fun setClicks() {

        binding.btnPay.setOnClickListener {
//            openPaymentDialog()
            showBottomSheet()
        }

    }

    private fun showBottomSheet() {
        val bind = LayoutDialogPaymentMethodBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(bind.root)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

        bind.btnCashOnDelivery.setOnClickListener {

            dialog.dismiss()
            binding.root.findNavController()
                .navigate(R.id.action_selectAddressFragment_to_paymentProgressFragment)

        }

        bind.btnPaytm.setOnClickListener {

            dialog.dismiss()
            binding.root.findNavController()
                .navigate(R.id.action_selectAddressFragment_to_paymentProgressFragment)

        }

        bind.btnDebitCard.setOnClickListener {

            dialog.dismiss()
            binding.root.findNavController()
                .navigate(R.id.action_selectAddressFragment_to_paymentProgressFragment)

        }

    }


    private fun setData() {

        binding.mrdButtonHome.setOnClickListener {

            if (binding.mrdButtonHome.isChecked) {

                binding.mrdButtonHome.isChecked = true
                binding.mrdButton.isChecked = false
                mrdHome = false
                mrdOffice = true

            } else {

                binding.mrdButtonHome.isChecked = false
                binding.mrdButton.isChecked = false
                mrdHome = true
                mrdOffice = false
            }

        }

        binding.mrdButton.setOnClickListener {

            if (binding.mrdButton.isChecked) {

                binding.mrdButton.isChecked = true
                binding.mrdButtonHome.isChecked = false
                mrdHome = true
                mrdOffice = false

            } else {

                binding.mrdButton.isChecked = false
                binding.mrdButtonHome.isChecked = false
                mrdHome = false
                mrdOffice = true
            }

        }

    }
}