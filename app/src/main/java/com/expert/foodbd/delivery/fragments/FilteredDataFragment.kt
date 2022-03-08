package com.expert.foodbd.delivery.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.expert.foodbd.delivery.models.GroupModel
import com.expert.foodbd.databinding.FragmentFilteredDataBinding
import com.expert.foodbd.delivery.activity.HomeActivity
import com.expert.foodbd.delivery.adapters.FilteredFoodsAdapters
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FilteredDataFragment : Fragment() {
    private var _binding: FragmentFilteredDataBinding? = null
    private val binding get() = _binding!!
    private val getFoods = ArrayList<GroupModel>()
    private var firebaseDatabase =
        FirebaseDatabase.getInstance("https://food-bd-default-rtdb.firebaseio.com/").reference.child(
            "foodDataNearby"
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFilteredDataBinding.inflate(layoutInflater, container, false)

        setClicks()

        return binding.root
    }

    private fun setClicks() {

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onPause() {
        super.onPause()
        binding.rlLoader.visibility = View.VISIBLE
    }


    private fun getData() {
        firebaseDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    binding.rlLoader.visibility = View.GONE

                    getFoods.clear()

                    for (snapshot in dataSnapshot.children) {

                        val groupModel: GroupModel? =
                            snapshot.getValue(GroupModel::class.java)
                        if (groupModel != null) {

                            if (groupModel.category == HomeActivity.CATEGORY_OBJECT) {

                                getFoods.add(groupModel)

                            }


                        } else {
                            Toast.makeText(
                                requireActivity(),
                                "Data is invalid",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }


                    val adapters = FilteredFoodsAdapters()
                    binding.rvPopularFoodsNearby.adapter = adapters
                    adapters.setData(viewItems = getFoods)

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

}