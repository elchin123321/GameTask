package com.example.gametask.data.firebase

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface SharedPreferencesDataSource {
    fun getLink(): String
    fun putLink(link: String)
    class Base @Inject constructor(@ApplicationContext private var context: Context) :
        SharedPreferencesDataSource {
        override fun getLink(): String {
            val sharedPref = context.getSharedPreferences(LINK_PREFERENCE, Context.MODE_PRIVATE)
            return sharedPref.getString(LINK, DEFAULT)?: DEFAULT
        }

        override fun putLink(link: String) {
            val sharedPref = context
                .getSharedPreferences(LINK_PREFERENCE, Context.MODE_PRIVATE)
            sharedPref
                .edit()
                .putString(LINK, link)
                .apply()
        }


        companion object {
            private const val LINK_PREFERENCE = "link_preference"
            private const val LINK = "link"
            const val DEFAULT = ""
        }

    }
}