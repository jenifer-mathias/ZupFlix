package br.com.zupflix.data.database

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    private val USER = "USER"

   val sharedPref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)

    fun getData(USER: String): String? {
        return sharedPref.getString(USER, "")
    }

    fun saveData(USER: String, email: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(USER, email)
        editor.apply()
    }

}

