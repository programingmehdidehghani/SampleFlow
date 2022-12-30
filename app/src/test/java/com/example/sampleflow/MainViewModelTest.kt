package com.example.sampleflow

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        viewModel = MainViewModel(testDispatchers)
    }

    @Test
    fun `countDownFlow, properly counts down from 5 to 0`() = runBlocking{
        viewModel.countDownFlow.test {
            for (i in 5 downTo 0){
                
                val emission = awaitItem()
                assertThat(emission).isEqualTo(i)
            }
        }
    }
}