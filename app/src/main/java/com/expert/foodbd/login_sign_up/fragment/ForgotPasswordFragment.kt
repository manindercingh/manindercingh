package com.expert.foodbd.login_sign_up.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.expert.foodbd.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

        setClicks()

        return binding.root
    }

    private fun setClicks() {

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

    }
}