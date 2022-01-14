package com.expert.foodbd.delivery.profile.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentProfileBinding
import com.expert.foodbd.utils.CompanionClass

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        setClicks()
        onBackButtonPressed()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CompanionClass.profile = "profile"

    }

    private fun onBackButtonPressed() {
        binding.root.isFocusableInTouchMode = true
        binding.root.requestFocus()
        binding.root.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            if (keyEvent.action === KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    CompanionClass.restaurant = ""
                    CompanionClass.food = ""
                    CompanionClass.home = ""
                    CompanionClass.dishDetails = ""
                    CompanionClass.profile = ""
                    Log.i("data", "CLEARED")
                    requireActivity().onBackPressed()
                    return@OnKeyListener true
                }
            }
            false
        })
    }

    private fun setClicks() {


        binding.imgBack.setOnClickListener {
            CompanionClass.restaurant = ""
            CompanionClass.food = ""
            CompanionClass.home = ""
            CompanionClass.dishDetails = ""
            CompanionClass.profile = ""
            Log.i("data", "CLEARED")
            requireActivity().onBackPressed()
        }

        binding.rlProfile.setOnClickListener {

            binding.root.findNavController()
                .navigate(R.id.action_profileFragment2_to_myProfileFragment)

        }

    }

}