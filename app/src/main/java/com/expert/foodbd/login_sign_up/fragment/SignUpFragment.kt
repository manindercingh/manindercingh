package com.expert.foodbd.login_sign_up.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentSignUpBinding
import com.expert.foodbd.login_sign_up.activity.MainActivity
import com.expert.foodbd.login_sign_up.activity.MainActivity.Companion.verificationCode
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private var firebaseAuth: FirebaseAuth? = null

    //    done defined is temporary TODO: set to strCheck ="" when needed
    private var strCheckName = ""
    private var strCheckPhone = ""
    private var mCallback: OnVerificationStateChangedCallbacks? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        textLive()
        setClick()
        setCallback()
        return binding.root

    }

    private fun setCallback() {
        firebaseAuth = FirebaseAuth.getInstance()
        mCallback = object : OnVerificationStateChangedCallbacks() {
            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(requireActivity(), "verification failed $e", Toast.LENGTH_SHORT)
                    .show()
                Log.i("HELLO", "FAILED VERIFICATION $e")
            }

            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                verificationCode = s
                Toast.makeText(
                    requireActivity(),
                    "OTP sent to ${MainActivity.phoneNum}",
                    Toast.LENGTH_SHORT
                ).show()
                binding.root.findNavController()
                    .navigate(R.id.action_signUpFragment_to_OTPRegisterFragment)

            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {}
        }
    }

    fun isValidPhone(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches()
    }

    private fun textLive() {

//        binding.edtPassword.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//
//                val strPassword: String = binding.edtPassword.text.toString()
//
//                if (strPassword.isNotEmpty() && strPassword.contains(" ")) {
//
//                    Toast.makeText(
//                        requireContext(),
//                        "Spaces not allowed Please re-write Password",
//                        Toast.LENGTH_SHORT
//                    ).show()
////                    binding.txtEmailError.visibility = View.VISIBLE
////                    CommonUtils.hideKeyboard(requireActivity())
////                    binding.edtPassword.setText("")
//
//                } else
//
//                    if (strPassword.length < 7) {
//
//                        binding.txtError.visibility = View.VISIBLE
//
//                        strCheck = ""
//
//                    } else {
//
//                        binding.txtError.visibility = View.INVISIBLE
//
////                    binding.btnCreate.isClickable = equals(true)
//
//                        strCheck = "done"
//
////                    binding.btnCreate.backgroundTintList =
////                        ColorStateList.valueOf(resources.getColor(R.color.app_color));
//                    }
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//        })

        binding.edtPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val strEmail: String = binding.edtPhone.text.toString()
                val emailPattern = Regex("""\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6}""")

//                if (!strEmail.matches(emailPattern)) {
//
//                    binding.txtEmailError.text = "Enter a valid Phone"
//                    binding.txtEmailError.visibility = View.VISIBLE
//
//                } else {
//
//                    binding.txtEmailError.visibility = View.INVISIBLE

                if (strEmail.length > 12) {
                    binding.txtEmailError.text = "Phone is too Long"
                    strCheckPhone = ""
                    binding.txtEmailError.visibility = View.VISIBLE
                } else if (strEmail.length < 9) {
                    binding.txtEmailError.text = "Enter a valid Phone"
                    strCheckPhone = ""
                    binding.txtEmailError.visibility = View.VISIBLE
                } else if (!isValidPhone(strEmail)) {

                    binding.txtEmailError.text = "Enter a valid Phone"
                    strCheckPhone = ""
                    binding.txtEmailError.visibility = View.VISIBLE

                } else if (strEmail.contains(" ")) {
                    binding.txtEmailError.text = "Enter a valid Phone"
                    strCheckPhone = ""
                    binding.txtEmailError.visibility = View.VISIBLE

                } else {

                    binding.txtEmailError.visibility = View.INVISIBLE
                    strCheckPhone = "done"

                }


            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.edtName.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                    val strName: String = binding.edtName.text.trim().toString()
                    val namePattern = Regex("^[A-Za-z]+$")

                    if (strName.length < 3) {
                        strCheckName = ""
                        binding.txtNameError.visibility = View.VISIBLE
//                    binding.btnCreate.isClickable = equals(false)
//
//                    binding.btnCreate.backgroundTintList =
//                        ColorStateList.valueOf(resources.getColor(R.color.dark_grey));

                    } else if (!strName.matches(namePattern)) {

                        strCheckName = ""
                        binding.txtNameError.visibility = View.VISIBLE
                        binding.txtNameError.text = "Add FirstName Only"

                    } else {

                        binding.txtNameError.visibility = View.INVISIBLE
                        strCheckName = "done"


                    }

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })

    }


    private fun setClick() {

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

        binding.btnCreate.setOnClickListener {

            if (binding.edtName.text.trim().toString() == "" && binding.edtPhone.text.trim()
                    .toString() == ""
            ) {

                Toast.makeText(
                    requireContext(),
                    "Please Check all fields properly",
                    Toast.LENGTH_SHORT
                ).show()


            } else if (strCheckName == "") {
                Toast.makeText(requireContext(), "Check Your Name", Toast.LENGTH_SHORT).show()
            } else if (strCheckPhone == "") {
                Toast.makeText(requireContext(), "Check Your Phone", Toast.LENGTH_SHORT).show()
            } else if (strCheckName == "done" && strCheckPhone == "done") {

                MainActivity.phoneNum = "+91" + binding.edtPhone.text.toString()
                MainActivity.name = binding.edtName.text.toString()

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    MainActivity.phoneNum,  // Phone number to verify
                    60,  // Timeout duration
                    TimeUnit.SECONDS,  // Unit of timeout
                    requireActivity(),  // Activity (for callback binding)
                    mCallback!!
                )


//                val actionCodeSettings =
//                    ActionCodeSettings.newBuilder() // URL you want to redirect back to. The domain (www.example.com) for this
//                        // URL must be whitelisted in the Firebase Console.
//                        .setUrl("http://fooddb.com") // This must be true
//                        .setHandleCodeInApp(true)
//                        .setIOSBundleId("com.example.ios")
//                        .setAndroidPackageName(
//                            "com.example.android",
//                            true,  /* installIfNotAvailable */
//                            "12" /* minimumVersion */
//                        )
//                        .build()

//                val auth = FirebaseAuth.getInstance()
//                auth.sendSignInLinkToEmail((binding.edtEmail.text.toString()), actionCodeSettings)
//                    .addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            Toast.makeText(requireContext(), "Email sent", Toast.LENGTH_SHORT)
//                                .show()
//
//                            binding.root.findNavController()
//                                .navigate(R.id.action_signUpFragment_to_OTPRegisterFragment)
//
//                        } else {
//
//                            Toast.makeText(requireContext(), "Email not sent", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }

            }

        }

    }

}
