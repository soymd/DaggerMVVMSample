package io.github.soymd.daggermvvm.main

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import javax.inject.Inject

interface MainRepository {
    fun saveCount(count: Int)
    fun getCount(): Int
}

class MainRepositoryImpl @Inject constructor(
    private val context: Context
) : MainRepository {
    override fun saveCount(count: Int) {
        getDefaultSharedPreferences(context).edit()
            .putInt(COUNT_KEY, count)
            .apply()
    }

    override fun getCount(): Int {
        return getDefaultSharedPreferences(context).getInt(
            COUNT_KEY, 0
        )
    }

    private companion object {
        const val COUNT_KEY = "count_key"
    }
}