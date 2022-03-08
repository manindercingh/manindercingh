package com.expert.foodbd.delivery.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.expert.foodbd.R
import com.expert.foodbd.databinding.ActivityHomeBinding
import com.expert.foodbd.login_sign_up.activity.MainActivity
import com.expert.foodbd.utils.setupWithNavController

class HomeActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityHomeBinding
    private var navController: LiveData<NavController>? = null

    companion object {
        var PRICE: String = ""
        var DISH_NAME: String = ""
        var TOTAL_PRICE: String = ""
        var CATEGORY_OBJECT: String = "noodles"
        var TOTAL_COUNT: Int = 1
        var FOOD_MAKES_YOU_HAPPY_COUNT: Int = 8
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        if (savedInstanceState == null) {
            setupBottomNav()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        setupBottomNav()
    }

    private fun setupBottomNav() {

        val graphIds = listOf(
            R.navigation.delivery_graph,
            R.navigation.dining_graph,
            R.navigation.pro_membership_graph,
            R.navigation.wallet_graph
        )

//        val controller = _binding.bottomNav.setupWithNavController(
//            graphIds,
//            supportFragmentManager,
//            R.id.nav,
//            intent
//        )

        val controller =
            _binding.bottomNav.setupWithNavController(
                graphIds,
                supportFragmentManager,
                R.id.nav,
                intent
            )

//        controller.observe(this) {
//            setupActionBarWithNavController(it)
//        }

        navController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.value?.navigateUp()!! || super.onSupportNavigateUp()
    }
}