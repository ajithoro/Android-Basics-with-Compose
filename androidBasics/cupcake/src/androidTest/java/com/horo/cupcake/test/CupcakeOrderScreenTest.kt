package com.horo.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.horo.cupcake.R
import com.horo.cupcake.data.DataSource
import com.horo.cupcake.model.CupcakeUiState
import com.horo.cupcake.ui.SelectOptionScreen
import com.horo.cupcake.ui.StartOrderScreen
import com.horo.cupcake.ui.SummaryScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderUiState = CupcakeUiState(
        quantity = 6,
        flavor = "Vanilla",
        pickUpDate = "Wed Jul 21",
        subTotal = "$100",
        pickUpOptionList = listOf()
    )

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(
                buttonList = DataSource.quantityList,
                onButtonClick = {}
            )
        }

        DataSource.quantityList.forEach {
            composeTestRule.onNodeWithStringId(it.first).assertIsDisplayed()
        }
    }

    @Test
    fun selectOptionScreen_verifyContent() {
        val flavorList = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subTotal = "$1300"

        composeTestRule.setContent {
            SelectOptionScreen(
                options = flavorList,
                selectedOption = "",
                optionOnClick = {},
                subTotal = subTotal,
                onCancelClick = {},
                onNextClick = {},
            )
        }

        flavorList.forEach {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, subTotal)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun selectOptionScreen_optionSelected_NextButtonEnabled() {
        val flavorList = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subTotal = "$1300"
        var selectedOption = ""

        TODO("Refactor this failing test")
        composeTestRule.setContent {
            SelectOptionScreen(
                options = flavorList,
                selectedOption = selectedOption,
                optionOnClick = {
                    selectedOption = it
                },
                subTotal = subTotal,
                onCancelClick = {},
                onNextClick = {},
            )
        }

        composeTestRule.onNodeWithText(flavorList.first()).performClick()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }

    @Test
    fun summaryScreen_verifyContentDisplay() {
        val numbOfCupcakes = composeTestRule.activity.resources.getQuantityString(
            R.plurals.cupcakes,
            fakeOrderUiState.quantity,
            fakeOrderUiState.quantity
        )
        val summaryItemList = listOf(
            Pair(composeTestRule.activity.getString(R.string.quantity), numbOfCupcakes),
            Pair(composeTestRule.activity.getString(R.string.flavor), fakeOrderUiState.flavor),
            Pair(
                composeTestRule.activity.getString(R.string.pickup_date),
                fakeOrderUiState.pickUpDate
            )
        )
        val subTotal = "$1300"

        composeTestRule.setContent {
            SummaryScreen(
                summaryList = summaryItemList,
                onSendClick = { },
                onCancelClick = { },
                subtotal = subTotal,
            )
        }

        composeTestRule.onNodeWithText(numbOfCupcakes).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.pickUpDate).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, subTotal)
        ).assertIsDisplayed()
    }
}
