package io.github.soymd.daggermvvm.main

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.github.soymd.daggermvvm.count.CountActivity
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
        subject = Robolectric.buildActivity(MainActivity::class.java, Intent())
            .create().start().resume().get()

        subject.binding.countActivityButton.performClick()

        val nextIntent = shadowOf(subject).peekNextStartedActivity()
        assertThat(
            nextIntent.component!!.className,
            equalTo(CountActivity::class.java.canonicalName)
        )

    }
}