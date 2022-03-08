package com.expert.foodbd.login_sign_up.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.delivery.activity.HomeActivity
import com.expert.foodbd.utils.App
import com.expert.foodbd.utils.AppConstants

class SplashFragment : Fragment() {
    private lateinit var sView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        sView = inflater.inflate(R.layout.fragment_splash, container, false)



        Handler().postDelayed({

            if (App.sharedPref?.getString(AppConstants.isLogin) == "yes") {

                val intent = Intent(requireActivity(), HomeActivity::class.java)
                startActivity(intent)

            } else {
                sView.findNavController()
                    .navigate(R.id.action_splashFragment_to_selectLoginSignUpFragment)
            }


        }, 3000)




        return sView
    }
}