package com.expert.foodbd.utils

import android.R
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar


object CommonUtils {

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

//    private var alertDialog: AlertDialog? = null
//    fun showSnackBar(activity: Activity, message: String?) {
//        Snackbar.make(activity.findViewById(R.id.content), message!!, Snackbar.LENGTH_SHORT).show()
//    }
//
//    fun onBackButtonPressed(requireActivity: Activity, sView: View) {
//        sView.setFocusableInTouchMode(true)
//        sView.requestFocus()
//        sView.setOnKeyListener(object : DialogInterface.OnKeyListener() {
//            fun onKey(view: View?, i: Int, keyEvent: KeyEvent): Boolean {
//                if (keyEvent.getAction() === KeyEvent.ACTION_DOWN) {
//                    if (i == KeyEvent.KEYCODE_BACK) {
//                        requireActivity.finishAffinity()
//                        return true
//                    }
//                }
//                return false
//            }
//        })
//    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

//    fun showDialog(activity: Activity) {
//        val alert: AlertDialog.Builder = AlertDialog.Builder(activity)
//        val mView: View = activity.layoutInflater.inflate(R.layout.dialog_progress, null)
//        alert.setView(mView)
//        alertDialog = alert.create()
//        alertDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        alertDialog.show()
//        alertDialog.setCanceledOnTouchOutside(false)
//    }
//
//    fun dismissDialog() {
//        alertDialog.dismiss()
//    }

//    fun isValidEmail(email: String): Boolean {
//        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
//        var flag = false
//        flag = email.matches(emailPattern)
//        return flag
//    }
//
//    fun isValidMobile(phone: String?): Boolean {
//        return Patterns.PHONE.matcher(phone).matches()
//    }
}