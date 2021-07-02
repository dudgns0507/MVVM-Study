package com.github.dudgns0507.mvvm_cropo.utils

import com.github.dudgns0507.mvvm_cropo.url

fun String.getUrl(): String {
    return "$url$this"
}