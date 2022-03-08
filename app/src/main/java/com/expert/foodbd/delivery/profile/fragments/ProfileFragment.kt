package com.expert.foodbd.delivery.profile.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentProfileBinding
import com.expert.foodbd.login_sign_up.activity.MainActivity
import com.expert.foodbd.utils.App

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

    private fun onBackButtonPressed() {
        binding.root.isFocusableInTouchMode = true
        binding.root.requestFocus()
        binding.root.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    requireActivity().onBackPressed()
                    return@OnKeyListener true
                }
            }
            false
        })
    }

    private fun setClicks() {


        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.rlProfile.setOnClickListener {

            binding.root.findNavController()
                .navigate(R.id.action_profileFragment2_to_myProfileFragment)

        }


        binding.btnLogout.setOnClickListener { getLogout() }


    }


    private fun getLogout() {
        val builder1 = AlertDialog.Builder(context)
        builder1.setTitle("LOGOUT")
        builder1.setMessage("Are you sure you want to logout ?")
        builder1.setCancelable(false)
        builder1.setPositiveButton("Yes") { dialog, _ ->
            App.sharedPref?.clearPreferences()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            dialog.cancel()
        }
        builder1.setNegativeButton(
            "No"
        ) { dialog: DialogInterface, _: Int -> dialog.cancel() }
        val alert11 = builder1.create()
        alert11.show()
    }

}