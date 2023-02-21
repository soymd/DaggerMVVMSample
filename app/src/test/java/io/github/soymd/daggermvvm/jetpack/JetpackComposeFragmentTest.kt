package io.github.soymd.daggermvvm.jetpack

import android.os.Looper.getMainLooper
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.github.soymd.daggermvvm.TestActivity
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
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
class JetpackComposeFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var subject: JetpackComposeFragment

    private lateinit var activity: TestActivity

    @BindValue
    lateinit var viewModel: ComposeViewModel

    @Before
    fun setUp() {
        viewModel = mockk(relaxed = true)
        subject = JetpackComposeFragment()
    }

    @Test
    fun closeButtonタップでfragmentを閉じる() {
        val channel = Channel<Unit>()
        every { viewModel.closeEvent } returns channel.receiveAsFlow()

        startFragment()

        assertThat(activity.supportFragmentManager.fragments.count(), equalTo(1))

        channel.trySend(Unit)
        shadowOf(getMainLooper()).idle()

        assertThat(activity.supportFragmentManager.fragments.count(), equalTo(0))
    }

    private fun startFragment() {
        activity = Robolectric.buildActivity(TestActivity::class.java)
            .create().start().resume().get()

        activity.supportFragmentManager
            .beginTransaction()
            .add(subject, "")
            .addToBackStack(null)
            .commit()
        shadowOf(getMainLooper()).idle()
    }
}