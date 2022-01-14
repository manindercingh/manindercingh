package com.expert.foodbd.delivery.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentUserVerifiedBinding

class UserVerifiedFragment : Fragment() {
    private var _binding: FragmentUserVerifiedBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserVerifiedBinding.inflate(layoutInflater, container, false)

        redirectToLogin()

        return binding.root
    }

    private fun redirectToLogin() {

        try {

            Handler().postDelayed({

                binding.root.findNavController()
                    .navigate(R.id.action_userVerifiedFragment_to_loginFragment)
            }, 5000)

        } catch (exp: Exception) {

            Log.i("TAGS", " $exp")

        }

    }

}