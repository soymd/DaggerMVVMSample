package io.github.soymd.daggermvvm.count

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CountViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: CountViewModel
    private lateinit var mockRepository: CountRepository


    @Before
    fun setUp() {
        mockRepository = mockk(relaxed = true)

        subject = CountViewModel(mockRepository)
    }

    @Test
    fun `countUp 現在保存されている値に1を足してliveDataに値を代入`() {
        //準備
        every { mockRepository.getCount() } answers { 10 }//getCountの返却値を指定

        //実行
        subject.countUp()

        //検証
        assertThat(subject.countLiveData.value, equalTo("11"))
    }

    @Test
    fun `countUp 現在保存されている値に1を足して保存する`() {
        //準備
        every { mockRepository.getCount() } answers { 1 }//getCountの返却値を指定

        //実行
        subject.countUp()

        //検証
        verify { mockRepository.saveCount(2) }//saveCountが引数2で実行されたかどうか検証
    }

    @Test
    fun `closeButtonTapped emit closeEvent`() {
        val mockObserver: Observer<Unit> = mockk(relaxed = true)
        subject.closeEvent.observeForever(mockObserver)

        subject.closeButtonTapped()

        verify { mockObserver.onChanged(Unit) }
    }
}