package com.expert.foodbd.delivery.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.GroupModel
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentDishDetailBinding
import com.expert.foodbd.delivery.activity.HomeActivity
import com.expert.foodbd.delivery.adapters.ChooseYourBeveragesAdapter
import com.expert.foodbd.delivery.adapters.ChooseYourSideAdapter
import com.expert.foodbd.utils.CompanionClass
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class DishDetailFragment : Fragment(), ChooseYourSideAdapter.SideClick {

    private var _binding: FragmentDishDetailBinding? = null
    private val binding get() = _binding!!
    private val chooseYourSide = ArrayList<GroupModel>()
    private var getPrice: Int = 0
    private var newCount: String = ""
    private var totalPrice: Int = 0
    private val chooseYourBeverages = ArrayList<GroupModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDishDetailBinding.inflate(layoutInflater, container, false)

        setAdapters()
        getData()
        addItemsFromJSONChooseYourSide()
        addItemsFromJSON()
        setClicks()

        onBackButtonPressed()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {

        getPrice = HomeActivity.TOTAL_PRICE.toInt()
        binding.btnAddItem.text = "Add Item $ ${HomeActivity.TOTAL_PRICE}"

        binding.txtDishName.text = HomeActivity.DISH_NAME
        binding.txtItemCount.text = HomeActivity.TOTAL_COUNT.toString()

    }

    private fun setAdapters() {
        val adapter = ChooseYourSideAdapter(this)
        binding.rvChooseYourSide.adapter = adapter
        adapter.setData(chooseYourSide)

        val beveragesAdapter = ChooseYourBeveragesAdapter()
        binding.rvChooseYourBeverages.adapter = beveragesAdapter
        beveragesAdapter.setData(chooseYourBeverages)

    }

    private fun addItemsFromJSONChooseYourSide() {
        try {
            val jsonDataString = readJsonChooseYourSide()
            val jsonArray = JSONArray(jsonDataString)
            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                val id = itemObj.getInt("id")
                val price = itemObj.getInt("price")
                val name = itemObj.getString("name")
                val groupModel = GroupModel(id, name, price)
                chooseYourSide.add(i, groupModel)

            }

        } catch (e: JSONException) {
            Log.d(ContentValues.TAG, "addItemsFromJSON: ", e)
        } catch (e: IOException) {
            Log.d(ContentValues.TAG, "addItemsFromJSON: ", e)
        }
    }

    @Throws(IOException::class)
    private fun readJsonChooseYourSide(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonString: String?
            inputStream = resources.openRawResource(R.raw.chooseyourside)
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonString = it } != null) {
                builder.append(jsonString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }

    private fun addItemsFromJSON() {
        try {
            val jsonDataString = readJSONDataFromFile()
            val jsonArray = JSONArray(jsonDataString)
            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                val id = itemObj.getInt("id")
                val price = itemObj.getInt("price")
                val name = itemObj.getString("name")
                val groupModel = GroupModel(id, name, price)
                chooseYourBeverages.add(i, groupModel)

            }

        } catch (e: JSONException) {
            Log.d(ContentValues.TAG, "addItemsFromJSON: ", e)
        } catch (e: IOException) {
            Log.d(ContentValues.TAG, "addItemsFromJSON: ", e)
        }
    }

    @Throws(IOException::class)
    private fun readJSONDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonString: String?
            inputStream = resources.openRawResource(R.raw.chooseyourbeverages)
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonString = it } != null) {
                builder.append(jsonString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }

    private fun onBackButtonPressed() {
        binding.root.isFocusableInTouchMode = true
        binding.root.requestFocus()
        binding.root.setOnKeyListener(View.OnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    CompanionClass.restaurant = ""
                    CompanionClass.food = ""
                    HomeActivity.PRICE = "0"
                    HomeActivity.TOTAL_COUNT = 1
                    CompanionClass.home = ""
                    CompanionClass.dishDetails = ""
                    Log.i("data", "CLEARED")
                    requireActivity().onBackPressed()
                    return@OnKeyListener true
                }
            }
            false
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setClicks() {


        CompanionClass.dishDetails = "dishDetails"

        binding.btnAddItem.setOnClickListener {

            binding.root.findNavController()
                .navigate(R.id.action_dishDetailFragment_to_addItemFragment)
        }

        binding.imgBack.setOnClickListener {
            CompanionClass.restaurant = ""
            CompanionClass.food = ""
            HomeActivity.PRICE = "0"
            HomeActivity.TOTAL_COUNT = 1
            CompanionClass.home = ""
            CompanionClass.dishDetails = ""
            Log.i("data", "CLEARED")
            requireActivity().onBackPressed()
        }

        binding.txtPlus.setOnClickListener {

            val count = binding.txtItemCount.text.toString().toInt()
            totalPrice = getPrice * (count + 1)

            HomeActivity.TOTAL_PRICE = totalPrice.toString()
            binding.btnAddItem.text = "Add Item $ $totalPrice"
            val countToInt: Int = count
            newCount = (countToInt + 1).toString()
            binding.txtItemCount.text = newCount
            HomeActivity.TOTAL_COUNT = newCount.toInt()

        }

        binding.txtMinus.setOnClickListener {

            val count: String = binding.txtItemCount.text.toString()
            val countToInt: Int = count.toInt()

            if (countToInt > 1) {

                val count = binding.txtItemCount.text.toString().toInt()
                val new = totalPrice - getPrice
                totalPrice = new
                binding.btnAddItem.text = "Add Item $ $new"
                val countToInt = count
                newCount = (countToInt - 1).toString()
                binding.txtItemCount.text = newCount

                HomeActivity.TOTAL_PRICE = new.toString()
                HomeActivity.TOTAL_COUNT = newCount.toInt()

            } else {
                Toast.makeText(requireContext(), "Choose one at least", Toast.LENGTH_SHORT).show()
            }


        }
    }

    override fun sideClick(price: Int) {


    }

}
