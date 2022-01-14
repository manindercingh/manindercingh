package com.expert.foodbd

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.expert.foodbd.databinding.FragmentFoodDeliveryDiningBinding
import com.expert.foodbd.dining.adapters.CustomTabLayoutAdapter
import com.expert.foodbd.dining.adapters.RestaurantDialogMenuAdapter
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class FoodDeliveryDiningFragment : Fragment(), CustomTabLayoutAdapter.Click,
    RestaurantDialogMenuAdapter.Click {
    private var _binding: FragmentFoodDeliveryDiningBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodDeliveryDiningBinding.inflate(layoutInflater, container, false)

        setAdapters()

        setClicks()

        return binding.root
    }

    private fun setClicks() {

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

        binding.llFloatingButton.setOnClickListener {
            if (binding.icDining.visibility == View.VISIBLE) {
                binding.icClose.visibility = View.VISIBLE
                binding.txtTitle.text = "Close"
                binding.icDining.visibility = View.GONE
                binding.rlFadedDialog.visibility = View.VISIBLE

            } else {

                binding.txtTitle.text = "Browse Menu"
                binding.icDining.visibility = View.VISIBLE
                binding.icClose.visibility = View.GONE
                binding.rlFadedDialog.visibility = View.GONE
            }
        }

    }

    private fun setAdapters() {

        val titles = arrayListOf<String>()

        titles.clear()
        titles.add("Recommended")
        titles.add("Soups")
        titles.add("Platters")
        titles.add("Appetizers")
        titles.add("Main Course")
        titles.add("Breads")
        titles.add("Rice and Biryani")
        titles.add("Fried Rice And Noodles")
        titles.add("Pasta")
        titles.add("Snacks")
        titles.add("Accompaniments")
        titles.add("Desserts")

        val customTabLayoutAdapter = CustomTabLayoutAdapter(requireActivity(), list = titles, this)
        binding.rvTabLayout.adapter = customTabLayoutAdapter

        val adapter = RestaurantDialogMenuAdapter(requireActivity(), list = titles, this)
        binding.rvMenus.adapter = adapter


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
//                getFood.add(i, groupModel);

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
            inputStream = resources.openRawResource(R.raw.food_menu_data)
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

    override fun click(position: Int) {


    }

    override fun menuClick(position: Int) {


    }

}