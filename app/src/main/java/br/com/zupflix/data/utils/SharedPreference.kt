package br.com.zupflix.data.utils

import android.content.Context
import android.content.SharedPreferences
import br.com.zupflix.utils.Constants.USER

class SharedPreference(val context: Context) {

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

