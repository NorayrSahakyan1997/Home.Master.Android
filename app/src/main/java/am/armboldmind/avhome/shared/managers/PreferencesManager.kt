package am.armboldmind.avhome.shared.managers

import am.armboldmind.avhome.AvHome
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PreferencesManager {
    private val PREF_NAME = "PREF_CONFIG"
    val instance: SharedPreferences by lazy { AvHome.instance.getSharedPreferences(
        PREF_NAME, Context.MODE_PRIVATE) }

    fun setString(key: String, value: String) { instance.edit().putString(key, value).apply() }
    fun setInt(key: String, value: Int) { instance.edit().putInt(key, value).apply() }
    fun setFloat(key: String, value: Float) { instance.edit().putFloat(key, value).apply() }
    fun setLong(key: String, value: Long) { instance.edit().putLong(key, value).apply() }
    fun setBoolean(key: String, value: Boolean?) { instance.edit().putBoolean(key, value!!).apply() }
    fun setObject(key: String, list: List<String>) { instance.edit().putString(key, Gson().toJson(list)).apply() }


    public fun getString(key: String, defaultValue: String? = null): String? { return instance.getString(key, defaultValue) }
    fun getInt(key: String, defaultValue: Int = -1): Int { return instance.getInt(key, defaultValue) }
    fun getFloat(key: String, defaultValue: Float = -1F): Float { return instance.getFloat(key, defaultValue) }
    fun getLong(key: String, defaultValue: Long = -1L): Long { return instance.getLong(key, defaultValue) }
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean { return instance.getBoolean(key, defaultValue) }
    fun getObject(key: String): List<String>? { return Gson().fromJson<List<String>>(instance.getString(key, null), object : TypeToken<List<String>>() {}.type) }

    fun remove(key: String) { instance.edit().remove(key).apply() }
    fun clear(): Boolean { return instance.edit().clear().commit() }
}