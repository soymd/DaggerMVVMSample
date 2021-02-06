package io.github.soymd.daggermvvm.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.soymd.daggermvvm.main.MainRepository
import io.github.soymd.daggermvvm.main.MainRepositoryImpl
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideMainRepository(context: Context): MainRepository {
        return MainRepositoryImpl(context)
    }
}
