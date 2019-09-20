package com.hsnozan.basemvvm.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun Activity.toast(message : CharSequence, duration : Int = Toast.LENGTH_LONG) =
    Toast.makeText(this,message,duration).show()

fun Activity.hideKeyboardFrom() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.getCurrentFocus()
    if (view == null) { view = View(this) }
    imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
}

inline fun Activity.alertDialog(body : AlertDialog.Builder.() -> AlertDialog.Builder) : AlertDialog {
    return AlertDialog.Builder(this)
        .body()
        .show()
}