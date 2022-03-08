package com.expert.foodbd.dining.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.FragmentBookTableBinding
import com.expert.foodbd.dining.adapters.DaysDiningAdapter
import com.expert.foodbd.dining.adapters.PeopleDiningAdapter
import com.expert.foodbd.dining.adapters.TimeDiningAdapter


class BookTableFragment : Fragment(), DaysDiningAdapter.Days, PeopleDiningAdapter.People,
    TimeDiningAdapter.Time {
    private var _binding: FragmentBookTableBinding? = null
    private val binding get() = _binding!!
    private var localDays: String = ""
    private var localPeople: String = ""
    private var localTime: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBookTableBinding.inflate(layoutInflater, container, false)

        setAdapters()
        setClicks()

        return binding.root
    }

    private fun setClicks() {

        binding.llNextProceed.setOnClickListener {

            binding.root.findNavController()
                .navigate(R.id.action_bookTableFragment_to_confirmInformationFragment)

        }

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }

    }

    private fun setAdapters() {

        val timesList = arrayListOf<String>()
        val people = arrayListOf<String>()

        timesList.clear()
        timesList.add("9:00 PM")
        timesList.add("9:30 PM")
        timesList.add("10:00 PM")
        timesList.add("10:30 PM")
        timesList.add("11:00 PM")
        timesList.add("11:30 PM")
        timesList.add("12:00 AM")

        people.clear()
        people.add("2")
        people.add("3")
        people.add("4")
        people.add("5")
        people.add("6")
        people.add("7")

        val daysAdapter = DaysDiningAdapter(requireActivity(), this)
        binding.rvDays.adapter = daysAdapter

        val peopleAdapter = PeopleDiningAdapter(requireContext(), people, this)
        binding.rvPeoples.adapter = peopleAdapter

        val timeAdapter = TimeDiningAdapter(requireContext(), timesList, this)
        binding.rvTime.adapter = timeAdapter

    }

    override fun days(days: String) {

        localDays = days

    }

    override fun peoplez(people: String) {
        localPeople = people

    }

    override fun time(time: String) {

        localTime = time

    }


}