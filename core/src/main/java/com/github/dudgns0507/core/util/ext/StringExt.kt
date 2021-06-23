package com.github.dudgns0507.core.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun String.getUrl(): String {
    return "https://jsonplaceholder.typicode.com/$this"
}