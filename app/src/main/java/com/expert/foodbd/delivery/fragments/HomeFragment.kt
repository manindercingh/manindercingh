package com.expert.foodbd.delivery.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.format.Formatter.formatIpAddress
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.GroupModel
import com.expert.foodbd.databinding.FragmentHomeBinding
import com.expert.foodbd.databinding.LayoutBottomSheetCuisinesBinding
import com.expert.foodbd.databinding.LayoutBottomSheetMoreFiltersBinding
import com.expert.foodbd.databinding.LayoutBottomSheetRelevanceBinding
import com.expert.foodbd.delivery.activity.HomeActivity.Companion.CATEGORY_OBJECT
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import com.expert.foodbd.R
import com.expert.foodbd.TopBrandsAdapter
import android.view.animation.TranslateAnimation
import com.expert.foodbd.delivery.activity.HomeActivity.Companion.FOOD_MAKES_YOU_HAPPY_COUNT
import com.expert.foodbd.delivery.adapters.*
import com.expert.foodbd.utils.CommonUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment(), CategoryFilterAdapter.OnCliQ {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val dataList = arrayListOf<String>()
    private val getFood = ArrayList<GroupModel>()
    private val foodsMakesYouHappyList = ArrayList<GroupModel>()
    private var firebaseDatabase =
        FirebaseDatabase.getInstance("https://food-bd-default-rtdb.firebaseio.com/").reference.child(
            "foodmakes"
        )
    private val foodMakesYouHappyAdapter = FoodMakesYouHappyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        dataList.clear()
        dataList.add("Fastest Delivery")
        dataList.add("Pure Veg")
        dataList.add("Rating 4.0+")
        dataList.add("Offers")
        dataList.add("Cuisines")
        dataList.add("New Arrivals")
        dataList.add("More")

        setAdapters()
        setClicks()
        getIpAddress()
        getDeviceDetails()
        addItemsFromJSON()
        onBackButtonPressed()

        return binding.root

    }

    private fun getIpAddress() {

        if (CommonUtils.isNetworkConnected(requireContext())) {

            val manager =
                requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            var ipAddress = formatIpAddress(manager.connectionInfo.ipAddress)

            binding.txtIpAddress.text = ipAddress
        } else {
            binding.txtIpAddress.text = "ipAddress"
        }

    }

    private fun getData() {
        firebaseDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    foodsMakesYouHappyList.clear()
                    for (snapshot in dataSnapshot.children) {

                        val groupModel: GroupModel? =
                            snapshot.getValue(GroupModel::class.java)
                        if (groupModel != null) {
                            foodsMakesYouHappyList.add(groupModel)
                        } else {
                            Toast.makeText(
                                requireActivity(),
                                "Data is invalid",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

//                    val adapters = FilteredFoodsAdapters()
//                    binding.rvPopularFoodsNearby.adapter = adapters
//                    adapters.setData(viewItems = foodsMakesYouHappyList)

                } else {
                    Toast.makeText(requireActivity(), "No Database Found", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(
                    requireActivity(),
                    "Something went wrong please try again later",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
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
                getFood.add(i, groupModel);

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

    @SuppressLint("HardwareIds")
    private fun getDeviceDetails() {

        val database = FirebaseDatabase.getInstance("https://food-bd-default-rtdb.firebaseio.com/")
        val myRef = database.getReference("MobileDetails")

        val androidId: String = Settings.Secure.getString(
            requireActivity().contentResolver,
            Settings.Secure.ANDROID_ID
        )

        val manager =
            requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        var ipAddress = formatIpAddress(manager.connectionInfo.ipAddress)

        val deviceName: String? = getDeviceName()

        myRef.child(deviceName.toString()).setValue("androidID$androidId").isComplete
        myRef.child("ipAddress").setValue(ipAddress).isComplete

    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.lowercase(Locale.getDefault())
                .startsWith(manufacturer.lowercase(Locale.getDefault()))
        ) {
            capitalize(model)
        } else {
            capitalize(manufacturer) + " " + model
        }
    }

    private fun capitalize(s: String?): String {
        if (s == null || s.isEmpty()) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            Character.toUpperCase(first).toString() + s.substring(1)
        }
    }

    private fun onBackButtonPressed() {
        binding.root.isFocusableInTouchMode = true
        binding.root.requestFocus()
        binding.root.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    requireActivity().finish()

                    return@OnKeyListener true
                }
            }
            false
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setClicks() {

        binding.llSeeLessMore.setOnClickListener {
            slideUp(binding.llSeeLessMore)
            foodMakesYouHappyAdapter.notifyDataSetChanged()
            if (FOOD_MAKES_YOU_HAPPY_COUNT == 8) {
                FOOD_MAKES_YOU_HAPPY_COUNT = 16
            } else {

                FOOD_MAKES_YOU_HAPPY_COUNT = 8
            }

        }

        binding.txtFilters.setOnClickListener { showBottomSheet() }

        binding.txtRestaurantNearby.setOnClickListener {

            val bundle = Bundle()
            bundle.putCharSequence("key", "txtRestaurantNearby")
            binding.root.findNavController()
                .navigate(R.id.action_homeFragment_to_viewAllListFragment, bundle)

        }

        binding.txtPopularFN.setOnClickListener {

            val bundle = Bundle()
            bundle.putCharSequence("key", "txtPopularFN")
            binding.root.findNavController()
                .navigate(R.id.action_homeFragment_to_viewAllListFragment, bundle)

        }

        binding.imgProfile.setOnClickListener {

            binding.root.findNavController()
                .navigate(R.id.action_homeFragment_to_profileFragment2)

        }

    }

    private fun showBottomSheet() {
        val navMain = LayoutBottomSheetRelevanceBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(navMain.root)
        bottomSheetDialog.show()
    }

    fun slideDown(view: View) {
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            0F,  // fromYDelta
            view.height.toFloat()
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    private fun showMoreFilters() {
        val navMain = LayoutBottomSheetMoreFiltersBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
//        bottomSheetDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.setContentView(navMain.root)
        bottomSheetDialog.show()
        navMain.txtClearAll.setOnClickListener {
            bottomSheetDialog.dismiss()
            Toast.makeText(requireContext(), "Cleared", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showCuisines() {

        val navMain = LayoutBottomSheetCuisinesBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(navMain.root)

        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
            "List 1",
            "List 2",
            "List 3",
            "List 4",
            "List 5"
        )
        ;
        arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, users)
        navMain.listPopularCuisines.adapter = arrayAdapter
        bottomSheetDialog.show()

    }

    private fun setAdapters() {

        val adapters = PopularFoodNearbyAdapters()
        binding.rvPopularFoodsNearby.adapter = adapters
        adapters.setData(viewItems = getFood)

        val categoryAdapter = CategoryFilterAdapter(requireContext(), list = dataList, this)
        binding.rvCategoryFilter.adapter = categoryAdapter

        val restaurantsAdapter = RestaurantNearbyAdapters()
        binding.rvNearbyRestaurants.adapter = restaurantsAdapter

        val topBrandsAdapter = TopBrandsAdapter()
        binding.rvTopBrands.adapter = topBrandsAdapter

        binding.rvFoods.adapter = foodMakesYouHappyAdapter


    }

    override fun oncliq(position: Int, cat: String) {

        CATEGORY_OBJECT = cat
        binding.root.findNavController().navigate(R.id.action_homeFragment_to_filteredDataFragment)

    }

    private fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0F
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    override fun openSheet(position: Int) {

        if (position == 6) {
            showMoreFilters()
        } else {
            showCuisines()
        }

    }

}