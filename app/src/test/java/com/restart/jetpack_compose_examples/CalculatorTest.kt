package com.restart.jetpack_compose_examples

import io.mockk.every
import io.mockk.mockkConstructor
import org.junit.Assert
import org.junit.Test

class CalculatorTest {

    @Test
    fun testMockContractor() {


        mockkConstructor(Summation::class)

        every { anyConstructed<Summation>().evaluateSummation() } returns -3


        val c = Calculator()

        Assert.assertEquals(-3, c.sum(110, 20))

    }
}