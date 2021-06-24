package com.github.dudgns0507.core.pref.type

import android.content.SharedPreferences
import android.text.TextUtils
import com.github.dudgns0507.core.pref.PrefManager
import kotlin.reflect.KProperty

open class BooleanPref(default: Boolean = false, name: String) :
    Preference<Boolean>(default, name) {
    override val prefs: SharedPreferences = PrefManager.instance.prefs

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        putPreference(name, value)
    }

    override fun findPreference(name: String, default: Boolean): Boolean = with(prefs) {
        return getBoolean(name, default)
    }

    override fun putPreference(name: String, value: Boolean) = with(prefs.edit()) {
        putBoolean(name, value).apply()
    }
}