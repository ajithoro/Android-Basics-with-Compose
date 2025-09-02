package com.horo.tiptime

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.horo.tiptime.ui.theme.AndroidBasicsTheme
import org.junit.Rule
import org.junit.Test

class TipTimeUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTipAmountUI() {
        composeTestRule.setContent {
            AndroidBasicsTheme {
                TipTimeContent(modifier = Modifier)
            }
        }
        composeTestRule.onNodeWithText("Bill amount")
            .performTextInput("100")
        composeTestRule.onNodeWithText("Tip percentage")
            .performTextInput("5.5")
        val expected = "£5.50"
        composeTestRule.onNodeWithText("Tip amount: $expected")
            .assertExists("No node exists with this name")
    }
}
