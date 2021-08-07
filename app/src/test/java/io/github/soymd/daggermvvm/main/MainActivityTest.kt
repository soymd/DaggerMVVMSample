package io.github.soymd.daggermvvm.main

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_fizzbuzz.view.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class, sdk = [28])
class MainActivityTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var subject: MainActivity

    @BindValue
    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun `onCreate fizzbuzzを表示`() {
        initActivity()

        assertThat(subject.fizzbuzzRecyclerView.childCount, equalTo(50))

        val item1 = subject.fizzbuzzRecyclerView.getChildAt(0)
        assertThat(item1.fizzbuzzText.text.toString(), equalTo("1"))

        val item3 = subject.fizzbuzzRecyclerView.getChildAt(2)
        assertThat(item3.fizzbuzzText.text.toString(), equalTo("Fizz"))

        val item5 = subject.fizzbuzzRecyclerView.getChildAt(4)
        assertThat(item5.fizzbuzzText.text.toString(), equalTo("Buzz"))

        val item15 = subject.fizzbuzzRecyclerView.getChildAt(14)
        assertThat(item15.fizzbuzzText.text.toString(), equalTo("FizzBuzz"))

        val item50 = subject.fizzbuzzRecyclerView.getChildAt(49)
        assertThat(item50.fizzbuzzText.text.toString(), equalTo("Buzz"))
    }

    private fun initActivity() {
        subject = Robolectric.buildActivity(MainActivity::class.java, Intent())
            .create().start().resume().get()

        subject.fizzbuzzRecyclerView.measure(0, 0)
        subject.fizzbuzzRecyclerView.layout(0, 0, 1440, 2340)
    }

}