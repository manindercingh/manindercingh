package com.expert.foodbd.login_sign_up.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.expert.foodbd.databinding.FragmentOtpLoginBinding
import com.expert.foodbd.login_sign_up.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.HashMap


class OtpLoginFragment : Fragment() {


    private var _binding: FragmentOtpLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var reference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOtpLoginBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        reference = FirebaseDatabase.getInstance().reference

        setClicks()

        return binding.root

    }


    private fun setClicks() {

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

        binding.btnVerify.setOnClickListener {

            val credential =
                PhoneAuthProvider.getCredential(
                    MainActivity.verificationCode,
                    binding.otpView.otp.toString()
                )
            signIn(credential)

            Log.i(
                "DATA",
                "USER DATA VERIFY $credential + ${MainActivity.verificationCode} + ${binding.otpView.otp.toString()}"
            )

        }

    }

    private fun signIn(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(requireContext(), "otp matched", Toast.LENGTH_SHORT).show()
                    val key: String = reference.push().key.toString()
                    val has = HashMap<String, String?>()
                    has["Phone Number"] = firebaseAuth.currentUser!!.phoneNumber
                    has["UId"] = firebaseAuth.uid
                    has["key"] = key
                    reference.child("My Users").child(MainActivity.phoneNum).child(
                        Objects.requireNonNull(
                            firebaseAuth.uid
                        ).toString()
                    ).setValue(has).addOnCompleteListener {

                        if (task.isSuccessful) {

//                            binding.root.findNavController().navigate(R.id.action_OTPRegisterFragment_to_userVerifiedFragment)

                            Log.i("TAG", "USER REGISTERED")

                        } else {

                            Toast.makeText(
                                requireContext(),
                                "User not Registered",
                                Toast.LENGTH_SHORT
                            )
                                .show()

                            Log.i("TAG", "USER REGISTERED not ")

                        }

                    }
                } else {

                    Toast.makeText(requireContext(), "Incorrect OTP", Toast.LENGTH_SHORT).show()

                }

            }

    }


}