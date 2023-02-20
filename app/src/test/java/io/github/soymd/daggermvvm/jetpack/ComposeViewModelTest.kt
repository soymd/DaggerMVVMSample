package io.github.soymd.daggermvvm.jetpack

import junit.framework.TestCase
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After

import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ComposeViewModelTest {
    private lateinit var subject: ComposeViewModel
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var testScope: TestScope

    @Before
    fun setUp() {
        subject = ComposeViewModel()

        testScope = TestScope(testDispatcher)

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun tapped() = runTest {

        var text = ""
        testScope.launch {
            subject.textFlow.collect {
                text = it
            }
        }

        subject.tapped()
        advanceUntilIdle()

        assertThat(text, equalTo("tapped"))
    }

    @Test
    fun tappedCloseButton() = runTest {
        var wasCalled = false
        testScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            subject.closeEvent.collect { wasCalled = true }
        }

        subject.tappedCloseButton()
        advanceUntilIdle()

        TestCase.assertTrue(wasCalled)
    }
}