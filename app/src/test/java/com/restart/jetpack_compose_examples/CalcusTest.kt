package com.restart.jetpack_compose_examples

import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.time.Duration


class CalcusTest {


    @Test
    fun testSuspendFunction() = runTest {
        val result = Calcus().invoke(2, 3)

        assert(result == 5) { "Expected 5, but got $result" }

        runCurrent()
    }

}