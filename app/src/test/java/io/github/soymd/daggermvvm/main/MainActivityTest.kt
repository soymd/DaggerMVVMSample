package io.github.soymd.daggermvvm.main

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.android.synthetic.main.activity_main.*
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
    @RelaxedMockK
    lateinit var mockViewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every { mockViewModel.countLiveData } answers { MutableLiveData() }
    }

    @Test
    fun `onCreate liveDataの値をcountTextで表示`() {
        val fakeCount = "99"
        every { mockViewModel.countLiveData.value } answers { fakeCount }

        subject = Robolectric.buildActivity(MainActivity::class.java, Intent())
            .create().start().resume().get()

        assertThat(subject.countText.text.toString(), equalTo(fakeCount))
    }

    @Test
    fun `countUpボタンタップでviewModel_countUp()を呼び出す`() {
        subject = Robolectric.buildActivity(MainActivity::class.java, Intent())
            .create().start().resume().get()

        subject.plusButton.performClick()

        verify(exactly = 1) { mockViewModel.countUp() }
    }

}