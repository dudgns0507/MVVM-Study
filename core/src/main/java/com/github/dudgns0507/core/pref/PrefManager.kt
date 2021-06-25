package com.github.dudgns0507.core.pref

import android.content.Context
import android.content.SharedPreferences

class PrefManager private constructor(
    private val context: Context,
    private val name: String = "DataManager",
    private val mode: Int = Context.MODE_PRIVATE
) {
    companion object {
        lateinit var context: Context
        var name: String = "DataManager"
        var mode: Int = Context.MODE_PRIVATE

        fun initialize(
            context: Context,
            name: String = context.packageName,
            mode: Int = Context.MODE_PRIVATE
        ) {
            Companion.context = context
            Companion.name = name
            Companion.mode = mode
        }

        val instance by lazy {
            PrefManager(context, name, mode)
        }
    }

    val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("DataManager", mode)
    }

    fun clear() = prefs.edit()?.clear()?.apply()
}