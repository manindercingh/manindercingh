package com.expert.foodbd.delivery.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentSettingsBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.util.*


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private var filepath: String = ""
    private var fileURL: String = ""
    private var sendStorageReference: StorageReference? = null
    var firebaseDatabase =
        FirebaseDatabase.getInstance("https://food-bd-default-rtdb.firebaseio.com/")
    var userMessageKeyRef = firebaseDatabase.reference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        sendStorageReference = FirebaseStorage.getInstance().getReference("send_image")
            .child("Image/" + UUID.randomUUID().toString())

        setClick()

        return binding.root

    }

    private fun upload(uploadPath: String) {

        sendStorageReference?.putFile(Uri.fromFile(File(uploadPath)))?.addOnSuccessListener(
            OnSuccessListener<Any?> {
                Toast.makeText(requireContext(), "Image is send", Toast.LENGTH_SHORT)
                    .show()
                sendStorageReference!!.getDownloadUrl()
                    .addOnSuccessListener(OnSuccessListener<Uri> { uri ->

                        val loadimageUri = uri.toString()

                        fileURL = loadimageUri

                        setData()

                        Log.i("data", "IMAGE URL  $filepath")

                    })
            })?.addOnFailureListener(OnFailureListener { e ->
            Toast.makeText(
                requireContext(),
                "on Failure-->>$e",
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    private fun setClick() {

        binding.btnUpload.setOnClickListener {

            if (binding.edtFoodname.text.trim().toString() == "" && binding.edtFoodname.text.trim()
                    .toString() == "" && filepath == ""
            ) {

                Toast.makeText(
                    requireContext(),
                    "Please fill all details properly",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                upload(filepath)

            }

        }

        binding.imgFood.setOnClickListener {

            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()

        }
    }

    @SuppressLint("CheckResult")
    private fun setData() {

        val data = "fooddata"
        userMessageKeyRef.child("FoodList")
            .child("UserFood")
            .child("fooddetails")
        val messagePushID: String = userMessageKeyRef.push().key!!
        val uploadData: MutableMap<String, String> = HashMap()
        uploadData["dishname"] = binding.edtFoodname.text.toString()
        uploadData["price"] = binding.edtPrice.text.toString()
        uploadData["messageID"] = messagePushID
        uploadData["foodImage"] = fileURL
        val messageBodyDetails: MutableMap<String, Any> = HashMap()
        messageBodyDetails["$data/$messagePushID"] = uploadData
        userMessageKeyRef.updateChildren(messageBodyDetails).addOnCompleteListener(
            OnCompleteListener { task: Task<Void?> ->
                if (task.isSuccessful) {

                    binding.edtPrice.text.clear()
                    binding.edtFoodname.text.clear()
                    Glide.with(requireContext()).load(R.drawable.app_logo)
                        .placeholder(R.drawable.app_logo)


                    Toast.makeText(
                        requireContext(),
                        "Data Uploaded",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!

            Toast.makeText(requireContext(), " ${uri.path}", Toast.LENGTH_SHORT).show()

            filepath = uri.path.toString()

            // Use Uri object instead of File to avoid storage permissions
            binding.imgFood.setImageURI(uri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Image Uploading Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

}