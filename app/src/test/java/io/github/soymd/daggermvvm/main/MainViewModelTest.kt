package io.github.soymd.daggermvvm.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule

class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: MainViewModel

    @Before
    fun setUp() {
        subject = MainViewModel()
    }

}