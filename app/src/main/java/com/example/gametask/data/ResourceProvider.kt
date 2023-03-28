package com.example.gametask.data

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ResourceProvider {
    fun string(@StringRes id: Int): String
    fun string(@StringRes id: Int, vararg args: Int?): String

    class Base @Inject constructor(@ApplicationContext private var context: Context):ResourceProvider {
        override fun string(id: Int): String {
            return context.getString(id)
        }

        override fun string(id: Int, vararg args: Int?): String {
            return context.getString(id, *args)
        }
    }
}