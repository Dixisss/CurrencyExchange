package com.exchange.rates.util.view

import android.support.design.widget.Snackbar
import android.view.View

fun View.showSnackBar(message: String, duration: Int) {
    Snackbar.make(this, message, duration).show()
}