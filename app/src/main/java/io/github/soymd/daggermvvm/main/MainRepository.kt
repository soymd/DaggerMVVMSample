package io.github.soymd.daggermvvm.main

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val context: Context
) {
    fun saveCount(count: Int) {
        getDefaultSharedPreferences(context).edit()
            .putInt(COUNT_KEY, count)
            .apply()
    }

    fun getCount(): Int {
        return getDefaultSharedPreferences(context).getInt(
            COUNT_KEY, 0
        )
    }

    private companion object {
        const val COUNT_KEY = "count_key"
    }
}

@Module
@InstallIn(SingletonComponent::class)//ActivityComponentだとエラー
object MainRepositoryModule {
    @Provides
    fun provideMainRepository(context: Context): MainRepository {
        return MainRepository(context)
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}