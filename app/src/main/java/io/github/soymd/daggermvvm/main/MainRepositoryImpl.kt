package io.github.soymd.daggermvvm.main

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
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

@Module
@InstallIn(ActivityComponent::class)
object MainRepositoryModule {
    @Provides
    fun provideMainRepository(application: Application): MainRepositoryImpl {
        return MainRepositoryImpl(application.applicationContext)
    }
}