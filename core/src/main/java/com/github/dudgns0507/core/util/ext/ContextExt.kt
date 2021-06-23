package com.github.dudgns0507.core.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri

fun Context.openBrowser(url: String) {
    val uri = Uri.parse(url)
    Intent(Intent.ACTION_VIEW, uri).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(this)
    }
}

fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.isDefaultNetworkActive
}