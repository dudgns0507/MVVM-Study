package com.github.dudgns0507.core.pref.type

import android.content.SharedPreferences
import android.text.TextUtils
import com.github.dudgns0507.core.pref.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class Preference<T : Any?>(
    val default: T, var name: String = ""
) : ReadWriteProperty<Any?, T> {

    open val prefs: SharedPreferences = PrefManager.instance.prefs

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        putPreference(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    protected open fun findPreference(name: String, default: T): T = with(prefs) {
        return when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> null
        } as T
    }

    protected open fun putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type cannot be saved to preferences without an adapter")
        }.apply()
    }
}