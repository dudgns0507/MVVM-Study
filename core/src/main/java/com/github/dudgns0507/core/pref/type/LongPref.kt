package com.github.dudgns0507.core.pref.type

import android.content.SharedPreferences
import android.text.TextUtils
import com.github.dudgns0507.core.pref.PrefManager
import kotlin.reflect.KProperty

open class LongPref(default: Long = 0L, name: String) : Preference<Long>(default, name) {
    override val prefs: SharedPreferences = PrefManager.instance.prefs

    override fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        putPreference(name, value)
    }

    override fun findPreference(name: String, default: Long): Long = with(prefs) {
        return getLong(name, default)
    }

    override fun putPreference(name: String, value: Long) = with(prefs.edit()) {
        putLong(name, value).apply()
    }
}