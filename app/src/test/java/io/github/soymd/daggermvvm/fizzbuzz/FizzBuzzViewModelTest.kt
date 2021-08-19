package io.github.soymd.daggermvvm.fizzbuzz

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FizzBuzzViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: FizzBuzzViewModel

    @Before
    fun setUp() {
        subject = FizzBuzzViewModel()
    }

    @Test
    fun `fizzbuzz 1のときは数字を文字列にして返却する`() {
        val actual = subject.fizzbuzz(1)
        MatcherAssert.assertThat(actual, CoreMatchers.equalTo("1"))
    }

    @Test
    fun `fizzbuzz 3の倍数のときはFizzを返却する`() {
        val actual = subject.fizzbuzz(3)
        MatcherAssert.assertThat(actual, CoreMatchers.equalTo("Fizz"))
    }

    @Test
    fun `fizzbuzz 5の倍数のときはBuzzを返却する`() {
        val actual = subject.fizzbuzz(5)
        MatcherAssert.assertThat(actual, CoreMatchers.equalTo("Buzz"))
    }

    @Test
    fun `fizzbuzz 3と5の倍数のときはFizzBuzzを返却する`() {
        val actual = subject.fizzbuzz(15)
        MatcherAssert.assertThat(actual, CoreMatchers.equalTo("FizzBuzz"))
    }
}