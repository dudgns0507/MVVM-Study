package com.github.dudgns0507.core.util.ext

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Parcelable
import android.widget.Toast
import com.github.dudgns0507.core.base.BaseActivity
import kotlin.reflect.KClass

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

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T> Context.moveTo(bundle: Parcelable? = null): Intent {
    val intent = Intent(this, T::class.java)
    intent.putExtra(BaseActivity.BUNDLE_KEY, bundle)
    return intent
}