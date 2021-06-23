package com.github.dudgns0507.core.pref.type

import android.content.SharedPreferences
import android.text.TextUtils
import com.github.dudgns0507.core.pref.PrefManager
import kotlin.reflect.KProperty

open class IntPref(default: Int = 0, name: String) : Preference<Int>(default, name) {
    override val prefs: SharedPreferences = PrefManager.instance.prefs

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        putPreference(name, value)
    }

    override fun findPreference(name: String, default: Int): Int = with(prefs) {
        return getInt(name, default)
    }

    override fun putPreference(name: String, value: Int) = with(prefs.edit()) {
        putInt(name, value).apply()
    }
}