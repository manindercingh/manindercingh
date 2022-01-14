package com.expert.foodbd.login_sign_up.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.expert.foodbd.R

class MainActivity : AppCompatActivity() {
    companion object {
        var verificationCode: String = ""
        var phoneNum: String = ""
        var name: String = ""
        var randomKey: String = ""
        var uid: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}