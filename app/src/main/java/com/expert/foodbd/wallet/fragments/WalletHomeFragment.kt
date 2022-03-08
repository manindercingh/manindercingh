package com.expert.foodbd.wallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentWalletHomeBinding


class WalletHomeFragment : Fragment() {
    private var _binding: FragmentWalletHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWalletHomeBinding.inflate(layoutInflater, container, false)

        setClicks()

        return binding.root

    }

    private fun setClicks() {

        binding.btnActivateWallet.setOnClickListener {

            binding.root.findNavController()
                .navigate(R.id.action_walletHomeFragment_to_activateWalletFragment)

        }

    }

}