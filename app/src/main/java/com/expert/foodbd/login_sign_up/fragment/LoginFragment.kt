package com.expert.foodbd.login_sign_up.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentLoginBinding
import com.expert.foodbd.delivery.activity.HomeActivity


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var strCheck: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)


        setClicks()
        textLive()

        return binding.root

    }

    private fun textLive() {

        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val strEmail: String = binding.edtEmail.text.toString()

                val phonePattern = Regex("^[+][0-9]{10,13}\$")

                if (!strEmail.matches(phonePattern)) {

                    binding.txtEmailError.visibility = View.VISIBLE

                } else {

                    binding.txtEmailError.visibility = View.INVISIBLE
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

//        binding.edtPassword.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//
//                val strPass: String = binding.edtPassword.text.toString()
//
////                if (strPass.isNotEmpty() && strPass.contains(" ")) {
////
////                    Toast.makeText(
////                        requireContext(),
////                        "Spaces not allowed Please re-write Password",
////                        Toast.LENGTH_SHORT
////                    )
////                        .show()
////                    CommonUtils.hideKeyboard(requireActivity())
////                    binding.edtPassword.setText("")
////
////                } else
//
//                if (strPass.length < 7) {
//
//                    strCheck = "wrongPass"
//
//                } else {
//
//                    strCheck = "done"
//
//                }
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//        })


    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    private fun setClicks() {

        binding.btnLogin.setOnClickListener {

            if (binding.edtEmail.text.trim().toString() == "") {

                Toast.makeText(
                    requireContext(),
                    "Please check Phone Number",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
            }


        }

        binding.txtForgotPassword.setOnClickListener {
            binding.root.findNavController()
                .navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

    }
}