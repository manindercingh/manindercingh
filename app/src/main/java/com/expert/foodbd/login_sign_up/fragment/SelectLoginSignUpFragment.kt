package com.expert.foodbd.login_sign_up.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentSelectLoginSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SelectLoginSignUpFragment : Fragment() {
    private var _binding: FragmentSelectLoginSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var reference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    @SuppressLint("HardwareIds")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSelectLoginSignUpBinding.inflate(inflater, container, false)
        reference = FirebaseDatabase.getInstance().reference

//        setData()

        // progressBar=findViewById(R.id.progressBar);
//        auth = FirebaseAuth.getInstance()
//        signUp("Maninder Singh", "maninder-singh@outlook.in", "passpass")
        setClicks()

        return binding.root

    }

    private fun setData() {

        val manager =
            requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        var ipAddress = Formatter.formatIpAddress(manager.connectionInfo.ipAddress)


        val key: String = reference!!.push().key.toString()
        val phone = "+9178145015987"
        val uid = "UID-UEWERTYUIO"
        val has = HashMap<String, String?>()
        has["Phone Number"] = phone
        has["UId"] = uid
        has["key"] = key
        has["ipAddress"] = ipAddress
        reference.child("My DATA").child("child 1").child("child 2").child("child 3")
            .setValue(has)
            .addOnCompleteListener {

                reference.child("My DATA").child("child 1").child("child 2").child("child 3")
                    .child("Cart").setValue(has)

            }
    }

//    private fun getDeviceName(): String? {
//        val manufacturer = Build.MANUFACTURER
//        val model = Build.MODEL
//        return if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
//            capitalize(model)
//        } else {
//            capitalize(manufacturer) + " " + model
//        }
//    }


//    private fun capitalize(s: String?): String {
//        if (s == null || s.length == 0) {
//            return ""
//        }
//        val first = s[0]
//        return if (Character.isUpperCase(first)) {
//            s
//        } else {
//            Character.toUpperCase(first).toString() + s.substring(1)
//        }
//    }


    private fun signUp(namez: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "otp matched", Toast.LENGTH_SHORT).show()
                    val key: String = reference.push().key.toString()
                    val has = HashMap<String, String?>()
                    has["name"] = namez
                    has["phone_Num"] = auth.currentUser!!.email
                    has["UId"] = auth.uid
                    has["UId"] = auth.uid
                    reference.child("Mere Users").child(email).setValue(has)
                        .addOnCompleteListener {

                            if (task.isSuccessful) {

//                            binding.root.findNavController().navigate(R.id.action_OTPRegisterFragment_to_userVerifiedFragment)


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


    private fun setClicks() {

        binding.btnSignIn.setOnClickListener {
            binding.root.findNavController()
                .navigate(R.id.action_selectLoginSignUpFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener {
            binding.root.findNavController()
                .navigate(R.id.action_selectLoginSignUpFragment_to_signUpFragment)
        }

    }

}