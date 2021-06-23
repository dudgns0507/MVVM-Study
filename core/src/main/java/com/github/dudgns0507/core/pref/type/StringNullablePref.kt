package com.github.dudgns0507.core.pref.type

import android.content.SharedPreferences
import android.text.TextUtils
import com.github.dudgns0507.core.pref.PrefManager
import kotlin.reflect.KProperty

open class StringNullablePref(default: String? = null, name: String) :
    Preference<String?>(default, name) {
    override val prefs: SharedPreferences = PrefManager.instance.prefs

    override fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
        if (TextUtils.isEmpty(name)) {
            name = "${property.name}_Preference"
        }
        putPreference(name, value)
    }

    override fun findPreference(name: String, default: String?): String? = with(prefs) {
        return getString(name, default)
    }

    override fun putPreference(name: String, value: String?) = with(prefs.edit()) {
        putString(name, value).apply()
    }
}