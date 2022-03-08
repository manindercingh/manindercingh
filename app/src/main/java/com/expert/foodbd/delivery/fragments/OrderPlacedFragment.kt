package com.expert.foodbd.delivery.fragments

import android.animation.TimeInterpolator
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentOrderPlacedBinding

class OrderPlacedFragment : Fragment() {
    private var _binding: FragmentOrderPlacedBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOrderPlacedBinding.inflate(layoutInflater, container, false)
        onBackButtonPressed()
        val freq = 3f
        val decay = 2
        val decayingSineWave = TimeInterpolator { input ->
            val raw = Math.sin(freq * input * 2 * Math.PI)
            (raw * Math.exp((-input * decay).toDouble())).toFloat()
        }

        val freq2 = 3f
        val decay2 = 2f
        val decayingSineWave2 = TimeInterpolator { input ->
            val raw = Math.sin(freq2 * input * 2 * Math.PI)
            (raw * Math.exp((-input * decay2).toDouble())).toFloat()
        }

        Handler().postDelayed({
            if (binding.btnHome.visibility == View.INVISIBLE) {
                binding.btnHome.visibility = View.VISIBLE
                binding.btnHome.animate()
                    .xBy(-80F)
                    .setInterpolator(decayingSineWave)
                    .setDuration(800)
                    .start();
            }
        }, 3000)

        Handler().postDelayed({
            if (binding.btnOrderDetails.visibility == View.INVISIBLE) {
                binding.btnOrderDetails.visibility = View.VISIBLE
                binding.btnOrderDetails.animate()
                    .xBy(80F)
                    .setInterpolator(decayingSineWave2)
                    .setDuration(800)
                    .start();
            }
        }, 3000)

        setClicks()

        return binding.root

    }

    private fun setClicks() {

        binding.btnHome.setOnClickListener {
            binding.root.findNavController()
                .navigate(R.id.action_orderPlacedFragment_to_deliveryFragment)
        }

        binding.btnOrderDetails.setOnClickListener {
            Toast.makeText(requireContext(), "Under Development", Toast.LENGTH_SHORT).show()
        }

    }

    private fun onBackButtonPressed() {
        binding.root.isFocusableInTouchMode = true
        binding.root.requestFocus()
        binding.root.setOnKeyListener(View.OnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK) {

                    binding.root.findNavController()
                        .navigate(R.id.action_orderPlacedFragment_to_deliveryFragment)

                    return@OnKeyListener true
                }
            }
            false
        })
    }

}