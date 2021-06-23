package com.github.dudgns0507.core.pref.type

import android.content.SharedPreferences
import android.text.TextUtils
import com.github.dudgns0507.core.pref.PrefManager
import kotlin.reflect.KProperty

open class FloatPref(default: Float = 0f, name: String) : Preference<Float>(default, name) {
    override val prefs: SharedPreferences = PrefManager.instance.prefs

    override fun getValue(thisRef: Any?, property: KProperty<*>): Float {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        putPreference(name, value)
    }

    override fun findPreference(name: String, default: Float): Float = with(prefs) {
        return getFloat(name, default)
    }

    override fun putPreference(name: String, value: Float) = with(prefs.edit()) {
        putFloat(name, value).apply()
    }
}