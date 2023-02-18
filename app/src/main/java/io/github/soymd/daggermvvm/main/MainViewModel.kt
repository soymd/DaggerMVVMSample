package io.github.soymd.daggermvvm.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val countActivityEvent = MutableLiveData<Unit>()
    fun countActivityButtonTapped() {
        countActivityEvent.value = Unit
    }

    val fizzBuzzFragmentEvent = MutableLiveData<Unit>()
    fun fizzBuzzFragmentButtonTapped() {
        fizzBuzzFragmentEvent.value = Unit
    }

    val jetpackFragmentEvent = MutableLiveData<Unit>()
    fun jetpackFragmentButtonTapped() {
        jetpackFragmentEvent.value = Unit
    }
}