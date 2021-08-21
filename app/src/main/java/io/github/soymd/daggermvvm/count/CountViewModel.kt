package io.github.soymd.daggermvvm.count

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountViewModel @Inject constructor(
    private val countRepository: CountRepository
) : ViewModel() {

    val countLiveData = MutableLiveData(countRepository.getCount().toString())

    fun countUp() {
        val newCount = countRepository.getCount() + 1
        countLiveData.value = newCount.toString()
        countRepository.saveCount(newCount)
    }

    val closeEvent = MutableLiveData<Unit>()
    fun closeButtonTapped() {
        closeEvent.value = null
    }
}