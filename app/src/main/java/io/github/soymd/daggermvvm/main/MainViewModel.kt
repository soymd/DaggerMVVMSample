package io.github.soymd.daggermvvm.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {
    val countLiveData = MutableLiveData(mainRepository.getCount().toString())

    fun countUp() {
        val newCount = mainRepository.getCount() + 1
        countLiveData.value = newCount.toString()
        mainRepository.saveCount(newCount)
    }

}