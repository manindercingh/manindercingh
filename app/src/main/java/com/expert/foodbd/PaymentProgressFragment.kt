package com.expert.foodbd

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.expert.foodbd.databinding.FragmentPaymentProgressBinding


class PaymentProgressFragment : Fragment() {
    private var _binding: FragmentPaymentProgressBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentProgressBinding.inflate(layoutInflater, container, false)

//        getNavigated()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNavigated()
    }

    private fun getNavigated() {

        Handler().postDelayed({
         binding.root.findNavController().navigate(R.id.action_paymentProgressFragment_to_orderPlacedFragment2)
        }, 3000)

    }



}