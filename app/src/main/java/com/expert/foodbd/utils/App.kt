package com.expert.foodbd.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context


class App : Application() {
    var context: Context? = null
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        sharedPref = SharedPref(context = applicationContext)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var sharedPref: SharedPref? = null
    }
}


