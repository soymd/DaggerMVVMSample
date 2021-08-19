package io.github.soymd.daggermvvm.fizzbuzz

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FizzBuzzViewModel @Inject constructor() : ViewModel() {
    fun fizzbuzz(i: Int): String {
        return when {
            i % 15 == 0 -> {
                "FizzBuzz"
            }
            i % 3 == 0 -> {
                "Fizz"
            }
            i % 5 == 0 -> {
                "Buzz"
            }
            else -> {
                i.toString()
            }
        }
    }
}