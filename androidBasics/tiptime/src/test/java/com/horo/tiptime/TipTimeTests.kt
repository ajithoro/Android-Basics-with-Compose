package com.horo.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test

class TipTimeTests {

    @Test
    fun testCalculateTipIsCorrectWithOutRoundUp() {
        val billAmount = 100.0
        val tipPercentage = 5.5
        val expectedTip = "£5.50"
        val actualTip = calculateTip(billAmount, tipPercentage, false)

        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun testCalculateTipIsCorrectWithRoundUp() {
        val billAmount = 100.0
        val tipPercentage = 5.5
        val expectedTip = "£6.00"
        val actualTip = calculateTip(billAmount, tipPercentage, true)

        assertEquals(expectedTip, actualTip)
    }
}
