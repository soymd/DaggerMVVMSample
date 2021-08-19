package io.github.soymd.daggermvvm.main

import android.content.Intent
import android.os.Looper.getMainLooper
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.github.soymd.daggermvvm.count.CountActivity
import io.github.soymd.daggermvvm.fizzbuzz.FizzBuzzFragment
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
    fun `onCreate countActivityButtonでCountActivityを呼び出し`() {
        //MainActivityを起動
        subject = Robolectric.buildActivity(MainActivity::class.java, Intent())
            .create().start().resume().get()

        subject.binding.countActivityButton.performClick()

        //Intentが飛んだことを確認
        val nextIntent = shadowOf(subject).peekNextStartedActivity()
        assertThat(
            nextIntent.component!!.className,
            equalTo(CountActivity::class.java.canonicalName)
        )
    }

    @Test
    fun `onCreate fizzBuzzFragmentButtonでFizzBuzzFragmentを呼び出し`() {
        subject = Robolectric.buildActivity(MainActivity::class.java, Intent())
            .create().start().resume().get()
        //fragmentが一つも生成されていないことを確認
        assertThat(subject.supportFragmentManager.fragments.count(), equalTo(0))

        subject.binding.fizzBuzzFragmentButton.performClick()

        //すべてのキューが実行されるまで待機
        shadowOf(getMainLooper()).idle()

        assertThat(subject.supportFragmentManager.fragments.count(), equalTo(1))
        assertThat(
            subject.supportFragmentManager.fragments[0].javaClass.canonicalName,
            equalTo(FizzBuzzFragment::class.java.canonicalName)
        )

    }
}