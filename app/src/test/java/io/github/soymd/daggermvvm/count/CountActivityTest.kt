package io.github.soymd.daggermvvm.count

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
import io.mockk.mockk
import io.mockk.verify
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
class CountActivityTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var subject: CountActivity

    @BindValue
    @RelaxedMockK
    lateinit var mockViewModel: CountViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every { mockViewModel.countLiveData } answers { MutableLiveData() }
    }

    @Test
    fun `onCreate liveDataの値をcountTextで表示`() {
        every { mockViewModel.countLiveData.value } answers { "99" }

        subject = Robolectric.buildActivity(CountActivity::class.java, Intent())
            .create().start().resume().get()

        assertThat(subject.binding.countText.text.toString(), equalTo("99"))
    }

    @Test
    fun `countUpボタンタップでviewModel_countUp()を呼び出す`() {
        subject = Robolectric.buildActivity(CountActivity::class.java, Intent())
            .create().start().resume().get()
        verify(exactly = 0) { mockViewModel.countUp() }//countUpがまだ呼ばれていないことを検証

        subject.binding.plusButton.performClick()

        verify(exactly = 1) { mockViewModel.countUp() }
    }

    @Test
    fun closeボタンタップでactivityを終了() {
        val liveData = MutableLiveData<Unit>()
        every { mockViewModel.closeEvent } returns liveData

        subject = Robolectric.buildActivity(CountActivity::class.java, Intent())
            .create().start().resume().get()

        subject.binding.countCloseButton.performClick()

        verify { mockViewModel.closeButtonTapped() }

        //viewModel.closeButtonTapped()で発火するイベントをテストコードから強制発火
        liveData.value = null

        assertThat(subject.isFinishing, equalTo(true))
    }
}