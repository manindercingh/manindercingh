package com.expert.foodbd.login_sign_up.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentOtpRegisterBinding
import com.expert.foodbd.login_sign_up.activity.MainActivity
import com.expert.foodbd.login_sign_up.activity.MainActivity.Companion.phoneNum
import com.expert.foodbd.login_sign_up.activity.MainActivity.Companion.verificationCode
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class OTPRegisterFragment : Fragment() {

    private var _binding: FragmentOtpRegisterBinding? = null
    private val binding get() = _binding!!
    private var firebaseDatabase =
        FirebaseDatabase.getInstance("https://food-bd-default-rtdb.firebaseio.com/").reference.child(
            "My Users"
        )

    private lateinit var reference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOtpRegisterBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        reference = FirebaseDatabase.getInstance().reference

        setClicks()

        return binding.root
    }

    private fun setClicks() {

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

        binding.btnVerify.setOnClickListener {

            val credential =
                PhoneAuthProvider.getCredential(verificationCode, binding.otpView.otp.toString())
            signInWithPhone(credential)

            Log.i(
                "DATA",
                "USER DATA VERIFY $credential + $verificationCode + ${binding.otpView.otp.toString()}"
            )

        }

    }

    private fun signInWithPhone(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "otp matched", Toast.LENGTH_SHORT).show()
                    val key: String = reference.push().key.toString()
                    val has = HashMap<String, String?>()
                    has["name"] = MainActivity.name
                    has["phone_Num"] = firebaseAuth.currentUser!!.phoneNumber
                    has["UId"] = firebaseAuth.uid
                    reference.child("My Users").child(phoneNum).setValue(has)
                        .addOnCompleteListener {

                            if (task.isSuccessful) {

                                binding.root.findNavController()
                                    .navigate(R.id.action_OTPRegisterFragment_to_userVerifiedFragment)


                            } else {

                                Toast.makeText(
                                    requireContext(),
                                    "User not Registered",
                                    Toast.LENGTH_SHORT
                                ).show()

                                Log.i("TAG", "USER REGISTERED not ")

                            }

                        }
                } else {

                    Toast.makeText(requireContext(), "Incorrect OTP", Toast.LENGTH_SHORT).show()

                }

            }

    }


//    private fun getData() {
//
//        firebaseDatabase.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                if (dataSnapshot.exists()) {
//
//                    myUsers.clear()
//
//                    for (snapshot in dataSnapshot.children) {
//
//                        val user: UserDataModel? =
//                            snapshot.getValue(UserDataModel::class.java)
//                        if (user != null) {
//
//                            if (user.phone == "+917589947986") {
//
//                                myUsers.add(user)
//
//                            } else {
//
//                                myUsers.add(user)
//
//                                Toast.makeText(
//                                    requireContext(),
//                                    "New User Registered $myUsers",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//
//                            }
//
//
//                        } else {
//                            Toast.makeText(
//                                requireContext(),
//                                "Data is invalid",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//
//
//                } else {
//                    Toast.makeText(requireContext(), "No Database Found", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//                Toast.makeText(
//                    requireContext(),
//                    "Something went wrong please try again later",
//                    Toast.LENGTH_SHORT
//                ).show()
//
//            }
//        })
//    }


}