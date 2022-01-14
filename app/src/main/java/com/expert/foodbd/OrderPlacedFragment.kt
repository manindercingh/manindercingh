package com.expert.foodbd

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat.getVisibility
import androidx.navigation.findNavController
import com.expert.foodbd.databinding.FragmentOrderPlacedBinding
import android.animation.TimeInterpolator
import android.view.KeyEvent
import android.widget.Toast


class OrderPlacedFragment : Fragment() {
    private var _binding: FragmentOrderPlacedBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOrderPlacedBinding.inflate(layoutInflater, container, false)
        onBackButtonPressed()
        val FREQ = 3f
        val DECAY = 2f
        // interpolator that goes 1 -> -1 -> 1 -> -1 in a sine wave pattern.
        // interpolator that goes 1 -> -1 -> 1 -> -1 in a sine wave pattern.
        val decayingSineWave = TimeInterpolator { input ->
            val raw = Math.sin(FREQ * input * 2 * Math.PI)
            (raw * Math.exp((-input * DECAY).toDouble())).toFloat()
        }

        val FREQ2 = 3f
        val DECAY2 = 2f
        // interpolator that goes 1 -> -1 -> 1 -> -1 in a sine wave pattern.
        // interpolator that goes 1 -> -1 -> 1 -> -1 in a sine wave pattern.
        val decayingSineWave2 = TimeInterpolator { input ->
            val raw = Math.sin(FREQ2 * input * 2 * Math.PI)
            (raw * Math.exp((-input * DECAY2).toDouble())).toFloat()
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
        binding.root.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            if (keyEvent.action === KeyEvent.ACTION_DOWN) {
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