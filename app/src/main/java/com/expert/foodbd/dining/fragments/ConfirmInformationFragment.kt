package com.expert.foodbd.dining.fragments

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
import com.expert.foodbd.databinding.FragmentConfirmInformationBinding

class ConfirmInformationFragment : Fragment() {
    private var _binding: FragmentConfirmInformationBinding? = null
    private val binding get() = _binding!!
    private var firstNameCheck: String = ""
    private var lastNameCheck: String = ""
    private var emailIDCheck: String = ""
    private var phoneCheck: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmInformationBinding.inflate(layoutInflater, container, false)

        textWatcher()
        setClicks()

        return binding.root
    }

    private fun textWatcher() {

        val emailPattern = Regex("""\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6}""")
        val namePattern = Regex("^[A-Za-z]+$")

        binding.edtFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                val firstname: String = binding.edtFirstName.text.toString()

                if (!firstname.matches(namePattern)) {

                    binding.edtFirstName.error = "not a valid name"
                    firstNameCheck = ""

                } else if (firstname.length > 20) {

                    binding.edtFirstName.error = "name is too long"
                    firstNameCheck = ""

                } else if (firstname.contains(" ")) {

                    binding.edtFirstName.error = "not a valid name"
                    firstNameCheck = ""

                } else {

                    firstNameCheck = "done"

                }


            }

        })

        binding.edtLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                val lastName: String = binding.edtLastName.text.toString().trim()

                if (!lastName.matches(namePattern)) {

                    binding.edtLastName.error = "not a valid name"
                    lastNameCheck = ""

                } else if (lastName.length > 25) {
                    binding.edtLastName.error = "Name is too long"
                    lastNameCheck = ""

                } else if (lastName.contains(" ")) {
                    binding.edtLastName.error = "not a valid name"
                    lastNameCheck = ""
                } else {
                    lastNameCheck = "done"
                }


            }

        })

        binding.edtEmail.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }

            override fun afterTextChanged(s: Editable?) {

                val email = binding.edtEmail.text.toString().trim()


                if (!email.matches(emailPattern)) {

                    binding.edtEmail.error = "not a valid email"
                    emailIDCheck = ""

                } else if (email.length > 50) {

                    binding.edtEmail.error = "email is too long"
                    emailIDCheck = ""

                } else if (email.contains(" ")) {
                    binding.edtEmail.error = "not a valid email"
                    emailIDCheck = ""
                } else {
                    emailIDCheck = "done"
                }


            }

        })

        binding.edtPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                val strPhone = binding.edtPhone.text.toString()

                if (strPhone.length > 12) {
                    binding.edtPhone.error = "phone number is too long"
                    phoneCheck = ""
                } else if (!isValidPhone(strPhone)) {

                    binding.edtPhone.error = "not a valid phone number"
                    phoneCheck = ""

                } else if (strPhone.contains(" ")) {
                    binding.edtPhone.error = "not a valid phone number"
                    phoneCheck = ""

                } else {
                    phoneCheck = "done"
                }


            }

        })

    }

    fun isValidPhone(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches()
    }

    private fun setClicks() {

        val emailPattern = Regex("""\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6}""")
        val name = Regex("^[A-Za-z]+$")
        val phone = Regex("@\"(?<!\\d)\\d{10}(?!\\d)\"")

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

        binding.btnSavChanges.setOnClickListener {

            if (emailIDCheck == "" || lastNameCheck == "" || firstNameCheck == "" || phoneCheck == "") {
                Toast.makeText(requireContext(), "please check your all fields", Toast.LENGTH_SHORT)
                    .show()

            } else {
                Toast.makeText(requireContext(), "done", Toast.LENGTH_SHORT).show()
            }

//            if (!binding.edtFirstName.text.toString().trim().matches(name)) {
//                Toast.makeText(requireContext(), "Not a Valid Name", Toast.LENGTH_SHORT).show()
//            } else if (!binding.edtLastName.text.toString().trim().matches(name)) {
//                Toast.makeText(requireContext(), "Not a Valid Name", Toast.LENGTH_SHORT).show()
//            } else if (binding.edtFirstName.text.toString()
//                    .trim().length > 25 || binding.edtLastName.text.toString()
//                    .trim().length > 25 || binding.edtLastName.text.toString().trim().length > 25
//            ) {
//                Toast.makeText(requireContext(), "Name is too long", Toast.LENGTH_SHORT).show()
//            } else if (!binding.edtEmail.text.toString().trim().matches(emailPattern)) {
//                Toast.makeText(requireContext(), "Not a Valid Email", Toast.LENGTH_SHORT).show()
//            } else if (binding.edtEmail.text.toString().trim().length > 60) {
//                Toast.makeText(requireContext(), "Email is too long", Toast.LENGTH_SHORT).show()
////            } else if (!isValidPhone(binding.edtPhone.text.toString().trim()) || binding.edtPhone.text.toString().trim().length < 9) {

//            } else if (binding.edtPhone.text.toString().trim()
//                    .matches(phone) || binding.edtPhone.text.toString()
//                    .trim().length < 9 || binding.edtPhone.text.toString().trim().contains(" ")
//            ) {
//                Toast.makeText(requireContext(), "Not a valid phone", Toast.LENGTH_SHORT).show()
//            } else if (binding.edtPhone.text.toString().trim().length > 12) {
//                Toast.makeText(requireContext(), "Phone number is too long", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
//            }

        }

    }
}