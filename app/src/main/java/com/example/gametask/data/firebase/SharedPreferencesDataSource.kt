package com.example.gametask.data.firebase

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface SharedPreferencesDataSource {
    fun getLink():String
    class Base @Inject constructor(@ApplicationContext private var context: Context):SharedPreferencesDataSource{
        override fun getLink(): String {
            val sharedPref = context.getSharedPreferences("url",Context.MODE_PRIVATE)
            return sharedPref.getString("url","")!!//todo
        }

    }
}