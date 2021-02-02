package io.github.soymd.daggermvvm.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.soymd.daggermvvm.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun mainActivity(): MainActivity

}
