package com.github.dudgns0507.core.util.ext

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.widget.Toast
import com.github.dudgns0507.core.base.BaseActivity

fun Context.openBrowser(url: String) {
    val uri = Uri.parse(url)
    Intent(Intent.ACTION_VIEW, uri).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(this)
    }
}

fun Context.isNetworkAvailable(): Boolean {
    var result = false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            val networkCapabilities = cm.activeNetwork ?: return false
            val actNw =
                cm.getNetworkCapabilities(networkCapabilities) ?: return false

            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        else -> {
            cm.run {
                cm.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }
    }

    return result
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T> Context.moveTo(bundle: Parcelable? = null): Intent {
    val intent = Intent(this, T::class.java)
    intent.putExtra(BaseActivity.BUNDLE_KEY, bundle)
    return intent
}