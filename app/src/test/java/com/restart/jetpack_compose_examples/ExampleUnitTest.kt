package com.restart.jetpack_compose_examples

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testContext() {
        val context: Context = ApplicationProvider.getApplicationContext()
    }


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testComposeUI() = runComposeUiTest {
        runTest {
            val counter = MutableStateFlow(0)
            setContent {
                val b by counter.collectAsState()
                Text(
                    modifier = Modifier.testTag("greeting"),
                    text = "counter: $b",
                )
            }


            onNodeWithTag("greeting").assertExists()
            onNodeWithText("counter: 0").assertExists()

            counter.emit(9)

            onNodeWithText("counter: 9").assertExists()
        }
    }

}