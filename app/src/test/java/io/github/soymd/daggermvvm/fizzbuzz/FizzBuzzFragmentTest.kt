package io.github.soymd.daggermvvm.fizzbuzz

import android.os.Looper.getMainLooper
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.github.soymd.daggermvvm.TestActivity
import kotlinx.android.synthetic.main.item_fizz_buzz.view.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class, sdk = [28])
class FizzBuzzFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var subject: FizzBuzzFragment
    private lateinit var activity: TestActivity

    @BindValue
    lateinit var viewModel: FizzBuzzViewModel

    @Before
    fun setUp() {
        viewModel = FizzBuzzViewModel()

        subject = FizzBuzzFragment()
    }

    @Test
    fun `onCreateView fizzbuzzを表示`() {
        startFragment()

        assertThat(
            subject.binding.fizzbuzzRecyclerView.childCount,
            equalTo(50)
        )

        val item1 = subject.binding.fizzbuzzRecyclerView.getChildAt(0)
        assertThat(item1.fizzbuzzText.text.toString(), equalTo("1"))

        val item3 = subject.binding.fizzbuzzRecyclerView.getChildAt(2)
        assertThat(
            item3.fizzbuzzText.text.toString(),
            equalTo("Fizz")
        )

        val item5 = subject.binding.fizzbuzzRecyclerView.getChildAt(4)
        assertThat(
            item5.fizzbuzzText.text.toString(),
            equalTo("Buzz")
        )

        val item15 = subject.binding.fizzbuzzRecyclerView.getChildAt(14)
        assertThat(
            item15.fizzbuzzText.text.toString(),
            equalTo("FizzBuzz")
        )

        val item50 = subject.binding.fizzbuzzRecyclerView.getChildAt(49)
        assertThat(
            item50.fizzbuzzText.text.toString(),
            equalTo("Buzz")
        )
    }

    @Test
    fun closeButtonタップでfragmentを閉じる() {
        startFragment()

        assertThat(
            activity.supportFragmentManager.fragments.count(),
            equalTo(1)
        )

        subject.binding.fizzBuzzCloseButton.performClick()

        shadowOf(getMainLooper()).idle()

        assertThat(
            activity.supportFragmentManager.fragments.count(),
            equalTo(0)
        )
    }

    private fun startFragment() {
        //Test用Activityを起動
        activity = Robolectric.buildActivity(TestActivity::class.java)
            .create().start().resume().get()
        //Fragmentを起動
        activity.supportFragmentManager
            .beginTransaction()
            .add(subject, "")
            .addToBackStack(null)
            .commit()

        //RecyclerViewのテストをする場合は以下が必要
        shadowOf(getMainLooper()).idle()
        subject.binding.fizzbuzzRecyclerView.measure(0, 0)
        subject.binding.fizzbuzzRecyclerView.layout(0, 0, 1440, 2340)
    }
}