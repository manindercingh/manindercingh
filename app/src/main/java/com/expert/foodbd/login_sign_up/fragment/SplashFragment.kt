package com.expert.foodbd.login_sign_up.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Handler
import androidx.navigation.findNavController
import com.expert.foodbd.R

class SplashFragment : Fragment() {
    private lateinit var sView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sView = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler().postDelayed({
            sView.findNavController()
                .navigate(R.id.action_splashFragment_to_selectLoginSignUpFragment)

        }, 3000)

        return sView
    }
}