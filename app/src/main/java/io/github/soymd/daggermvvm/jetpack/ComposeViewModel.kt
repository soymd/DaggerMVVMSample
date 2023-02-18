package io.github.soymd.daggermvvm.jetpack

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComposeViewModel @Inject constructor() : ViewModel() {
    fun tapped() {
        println("tapped")
    }
}