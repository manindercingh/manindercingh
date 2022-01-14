package com.expert.foodbd.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


@SuppressLint("CommitPrefEdits")
class SharedPref(context: Context) {
    private val context: Context = context
    var prefsEditor: SharedPreferences.Editor
    var sharedpreferences: SharedPreferences =
        context.getSharedPreferences("SERVICES", Context.MODE_PRIVATE)

    fun saveString(key: String?, value: String?) {
        val editor = sharedpreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?): String? {
        return sharedpreferences.getString(key, "")
    }

    fun clearPreferences() {
        val editor = sharedpreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun <T> getModel(key: String?, type: Class<T>?): T {
        val gson = Gson()
        return gson.fromJson(sharedpreferences.getString(key, ""), type)
    }

    fun saveModel(key: String?, modelClass: Any?) {
        val gson = Gson()
        prefsEditor.putString(key, gson.toJson(modelClass))
        prefsEditor.apply()
    }

    init {
        prefsEditor = sharedpreferences.edit()
    }
}