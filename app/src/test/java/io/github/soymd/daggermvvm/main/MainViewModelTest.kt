package io.github.soymd.daggermvvm.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: MainViewModel
    private lateinit var mockRepository: MainRepository


    @Before
    fun setUp() {
        mockRepository = mockk(relaxed = true)

        subject = MainViewModel(mockRepository)
    }

    @Test
    fun `countUp 現在保存されている値に1を足してliveDataに値を代入 mockライブラリ使用`() {
        //準備
        every { mockRepository.getCount() } answers { 10 }//getCountの返却値を指定

        //実行
        subject.countUp()

        //検証
        assertThat(subject.countLiveData.value, equalTo("11"))
    }

    @Test
    fun `countUp 現在保存されている値に1を足して保存する mockライブラリ使用`() {
        //準備
        every { mockRepository.getCount() } answers { 1 }//getCountの返却値を指定

        //実行
        subject.countUp()

        //検証
        verify { mockRepository.saveCount(2) }//saveCountが引数2で実行されたかどうか検証
    }
}