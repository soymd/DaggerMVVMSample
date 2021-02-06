package io.github.soymd.daggermvvm.main

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import io.github.soymd.daggermvvm.common.ArgumentKeys
import io.github.soymd.daggermvvm.di.AppViewModelProviders
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class MainActivityTest {
    private lateinit var subject: MainActivity

    private lateinit var mockViewModel: MainViewModel

    lateinit var mockViewModelProviders: AppViewModelProviders

    lateinit var mockViewModelProvider: ViewModelProvider

    lateinit var intent: Intent

    @Before
    fun setUp() {
        mockViewModel = mockk(relaxed = true)
        mockViewModelProviders = mockk(relaxed = true)
        mockViewModelProvider = mockk(relaxed = true)

        every { mockViewModelProviders.of(any(), any()) } returns mockViewModelProvider
        every { mockViewModelProvider.get(MainViewModel::class.java) } returns mockViewModel

        intent = Intent().apply {
            putExtra(ArgumentKeys.VIEW_MODEL_PROVIDERS.key, mockViewModelProviders)
        }

        every { mockViewModel.countLiveData } answers { MutableLiveData() }
    }

    @Test
    fun `onCreate liveDataの値をcountTextで表示`() {
        val fakeCount = "99"
        every { mockViewModel.countLiveData.value } answers { fakeCount }

        subject = Robolectric.buildActivity(MainActivity::class.java, intent)
            .create().start().resume().get()

        assertThat(subject.countText.text.toString(), equalTo(fakeCount))
    }

    @Test
    fun `countUpボタンタップでviewModel_countUp()を呼び出す`() {
        subject = Robolectric.buildActivity(MainActivity::class.java, intent)
            .create().start().resume().get()

        subject.plusButton.performClick()

        verify(exactly = 1) { mockViewModel.countUp() }
    }

}