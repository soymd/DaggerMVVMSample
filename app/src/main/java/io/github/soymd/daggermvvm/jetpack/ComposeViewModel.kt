package io.github.soymd.daggermvvm.jetpack

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ComposeViewModel @Inject constructor() : ViewModel() {
    private val _textFlow = MutableStateFlow("Hello, Jetpack Compose.")
    val textFlow: StateFlow<String> = _textFlow.asStateFlow()

    fun tapped() {
        println("tapped")
        _textFlow.value = "tapped"
    }
}