package com.expert.foodbd.delivery.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentViewAllListBinding
import com.expert.foodbd.delivery.adapters.PopularFoodNearbyAdaptersInFragment
import com.expert.foodbd.delivery.adapters.RestaurantNearbyActivityAdapters
import com.expert.foodbd.delivery.models.GroupModel
import com.expert.foodbd.utils.CompanionClass
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class ViewAllListFragment : Fragment() {
    private var _binding: FragmentViewAllListBinding? = null
    private val binding get() = _binding!!
    private val viewItems = ArrayList<GroupModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewAllListBinding.inflate(layoutInflater, container, false)

        setData()

        addItemsFromJSON()

        setClicks()

        onBackButtonPressed()

        return binding.root
    }

    private fun setClicks() {

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

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
                viewItems.add(i, groupModel);

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
            inputStream = resources.openRawResource(R.raw.fooddatatoshow)
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
        binding.root.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            if (keyEvent.action === KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    CompanionClass.restaurant = ""
                    CompanionClass.food = ""
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments

        when (bundle?.get("key") as String) {
            "txtPopularFN" -> {
                CompanionClass.food = "food"
            }
            "txtRestaurantNearby" -> {
                CompanionClass.restaurant = "restaurant"
            }
            else -> {
                Log.i("hey", "DATA is HERE at ELSE")
            }


        }

    }

    private fun setData() {
        val bundle = arguments
        val check: String = bundle?.get("key") as String

        val adapters = PopularFoodNearbyAdaptersInFragment()
        binding.rvPopularFoodsNearby.adapter = adapters
        adapters.setData(viewItems)

        val ad = RestaurantNearbyActivityAdapters()
        binding.rvNearbyRestaurants.adapter = ad

        if (check == "txtPopularFN") {

            binding.txtTitle.text = "Popular Food Nearby"
            binding.rvPopularFoodsNearby.visibility = View.VISIBLE
            binding.rvNearbyRestaurants.visibility = View.INVISIBLE

        } else if (check == "txtRestaurantNearby") {

            binding.txtTitle.text = "Nearby Restaurants"
            binding.rvPopularFoodsNearby.visibility = View.INVISIBLE
            binding.rvNearbyRestaurants.visibility = View.VISIBLE

        } else {
            Log.i("hey", "DATA is HERE at ELSE")
        }


        if (CompanionClass.dishDetails == "dishDetails") {

            CompanionClass.restaurant = ""
            CompanionClass.food = ""
            CompanionClass.home = ""
            CompanionClass.dishDetails = ""
            binding.root.findNavController()
                .navigate(R.id.action_viewAllListFragment_to_dishDetailFragment)
        }

    }


}