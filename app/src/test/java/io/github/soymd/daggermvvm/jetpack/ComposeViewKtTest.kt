package io.github.soymd.daggermvvm.jetpack

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.github.soymd.daggermvvm.TestActivity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class, sdk = [29])
class ComposeViewKtTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    private lateinit var mockViewModel: ComposeViewModel

    @Before
    fun setUp() {
        hiltRule.inject()

        mockViewModel = mockk(relaxed = true)
        every { mockViewModel.textFlow } returns MutableStateFlow("")
    }

    @Test
    fun showText() {
        val mutableStateFlow = MutableStateFlow("fake-text1")
        every { mockViewModel.textFlow } returns mutableStateFlow

        composeTestRule.setContent { ComposeView(mockViewModel) }

        composeTestRule.onNodeWithText("fake-text1").assertIsDisplayed()

        mutableStateFlow.value = "fake-text"

        composeTestRule.onNodeWithText("fake-text").assertIsDisplayed()
    }

    @Test
    fun `tap button`() {
        composeTestRule.setContent { ComposeView(mockViewModel) }
        val button = composeTestRule.onNodeWithText("button")

        button.performClick()

        verify { mockViewModel.tapped() }
    }
}