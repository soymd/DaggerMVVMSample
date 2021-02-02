package io.github.soymd.daggermvvm.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class MainApplication @Inject constructor(): DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
