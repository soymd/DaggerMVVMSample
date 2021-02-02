package io.github.soymd.daggermvvm.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.soymd.daggermvvm.di.MainApplication

@Module
class AppModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}
