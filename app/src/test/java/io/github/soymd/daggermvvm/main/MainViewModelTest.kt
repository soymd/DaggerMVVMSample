package io.github.soymd.daggermvvm.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: MainViewModel

    @Before
    fun setUp() {
        subject = MainViewModel()
    }

    @Test
    fun `fizzbuzz 1のときは数字を文字列にして返却する`() {
        val actual = subject.fizzbuzz(1)
        assertThat(actual, equalTo("1"))
    }

    @Test
    fun `fizzbuzz 3の倍数のときはFizzを返却する`() {
        val actual = subject.fizzbuzz(3)
        assertThat(actual, equalTo("Fizz"))
    }

    @Test
    fun `fizzbuzz 5の倍数のときはBuzzを返却する`() {
        val actual = subject.fizzbuzz(5)
        assertThat(actual, equalTo("Buzz"))
    }

    @Test
    fun `fizzbuzz 3と5の倍数のときはFizzBuzzを返却する`() {
        val actual = subject.fizzbuzz(15)
        assertThat(actual, equalTo("FizzBuzz"))
    }

}