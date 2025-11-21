package com.horo.lunchtray.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.horo.lunchtray.LunchTrayScreen
import com.horo.lunchtray.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LunchTrayNavigationTest {

    @get:Rule
    val composeAndroidComposeTestRule = createAndroidComposeRule<ComponentActivity>()
    lateinit var navController: TestNavHostController

    @Before
    fun setNavController() {
        composeAndroidComposeTestRule.setContent {
            navController = TestNavHostController(composeAndroidComposeTestRule.activity).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LunchTrayScreen(
                navController = navController
            )
        }
    }

    @Test
    fun startOrderScreen_navigationUpButton_shouldNotBeDisplayed() {
        composeAndroidComposeTestRule.onNodeWithContentDescription(
            composeAndroidComposeTestRule.activity.getString(R.string.back_button)
        ).assertIsNotDisplayed()
    }

    @Test
    fun chooseEntreeScreen_navigationUpButton_shouldBeDisplayed() {
        navigateToChooseEntreeScreen()
        composeAndroidComposeTestRule.onNodeWithContentDescription(
            composeAndroidComposeTestRule.activity.getString(R.string.back_button)
        ).assertIsDisplayed()
    }

    @Test
    fun chooseEntreeScreen_onNavigationUpButtonClick_navigatesToStartOrderScreen() {
        navigateToChooseEntreeScreen()
        composeAndroidComposeTestRule.onNodeWithContentDescription(
            composeAndroidComposeTestRule.activity.getString(R.string.back_button)
        ).performClick()

        navController.assertScreenName(LunchTrayScreen.StartOrder.name)
    }

    @Test
    fun chooseSideDishScreen_navigationUpButton_shouldBeDisplayed() {
        navigateToChooseSideDishScreen()
        composeAndroidComposeTestRule.onNodeWithContentDescription(
            composeAndroidComposeTestRule.activity.getString(R.string.back_button)
        ).assertIsDisplayed()
    }

    @Test
    fun chooseSideDishScreen_onNavigationUpButtonClick_navigatesToChooseEntreeScreen() {
        navigateToChooseSideDishScreen()
        composeAndroidComposeTestRule.onNodeWithContentDescription(
            composeAndroidComposeTestRule.activity.getString(R.string.back_button)
        ).performClick()

        navController.assertScreenName(LunchTrayScreen.ChooseEntree.name)
    }

    @Test
    fun chooseAccompanimentScreen_navigationUpButton_shouldBeDisplayed() {
        navigateToChooseAccompanimentScreen()
        composeAndroidComposeTestRule.onNodeWithContentDescription(
            composeAndroidComposeTestRule.activity.getString(R.string.back_button)
        ).assertIsDisplayed()
    }

    @Test
    fun chooseAccompanimentScreen_onNavigationUpButtonClick_navigatesToChooseSideDishScreen() {
        navigateToChooseAccompanimentScreen()
        composeAndroidComposeTestRule.onNodeWithContentDescription(
            composeAndroidComposeTestRule.activity.getString(R.string.back_button)
        ).performClick()

        navController.assertScreenName(LunchTrayScreen.ChooseSideDish.name)
    }

    @Test
    fun chooseOrderCheckoutScreen_navigationUpButton_shouldBeDisplayed() {
        navigateToOrderCheckoutScreen()
        composeAndroidComposeTestRule.onNodeWithContentDescription(
            composeAndroidComposeTestRule.activity.getString(R.string.back_button)
        ).assertIsDisplayed()
    }

    @Test
    fun chooseOrderCheckoutScreen_onNavigationUpButtonClick_navigatesToChooseAccompanimentScreen() {
        navigateToOrderCheckoutScreen()
        composeAndroidComposeTestRule.onNodeWithContentDescription(
            composeAndroidComposeTestRule.activity.getString(R.string.back_button)
        ).performClick()

        navController.assertScreenName(LunchTrayScreen.ChooseAccompaniment.name)
    }

    @Test
    fun lunchTrayScreen_verifyDefaultScreen() {
        navController.assertScreenName(LunchTrayScreen.StartOrder.name)
    }

    @Test
    fun startOrderScreen_onStartOrderButtonClick_navigatesToChooseEntreeScreen() {
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.start_order)
            .performClick()
        navController.assertScreenName(LunchTrayScreen.ChooseEntree.name)
    }

    @Test
    fun chooseEntreeScreen_onNextButtonClick_navigatesToChooseSideDishScreen() {
        navigateToChooseEntreeScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
        navController.assertScreenName(LunchTrayScreen.ChooseSideDish.name)
    }

    @Test
    fun chooseEntreeScreen_onCancelButtonClick_navigatesToStartOrderScreen() {
        navigateToChooseEntreeScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
        navController.assertScreenName(LunchTrayScreen.StartOrder.name)
    }

    @Test
    fun chooseSideDishScreen_onNextButtonClick_navigatesToChooseAccompanimentScreen() {
        navigateToChooseSideDishScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
        navController.assertScreenName(LunchTrayScreen.ChooseAccompaniment.name)
    }

    @Test
    fun chooseSideDishScreen_onCancelButtonClick_navigatesToStartOrderScreen() {
        navigateToChooseSideDishScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
        navController.assertScreenName(LunchTrayScreen.StartOrder.name)
    }

    @Test
    fun chooseAccompanimentScreen_onNextButtonClick_navigatesToOrderCheckoutScreen() {
        navigateToChooseAccompanimentScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
        navController.assertScreenName(LunchTrayScreen.OrderCheckout.name)
    }

    @Test
    fun chooseAccompanimentScreen_onCancelButtonClick_navigatesToStartOrderScreen() {
        navigateToChooseAccompanimentScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
        navController.assertScreenName(LunchTrayScreen.StartOrder.name)
    }

    @Test
    fun orderCheckoutScreen_onSubmitButtonClick_navigatesToStartOrderScreen() {
        navigateToOrderCheckoutScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.submit)
            .performClick()
        navController.assertScreenName(LunchTrayScreen.StartOrder.name)
    }

    @Test
    fun orderCheckoutScreen_onCancelButtonClick_navigatesToStartOrderScreen() {
        navigateToOrderCheckoutScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
        navController.assertScreenName(LunchTrayScreen.StartOrder.name)
    }

    private fun NavHostController.assertScreenName(screenName: String) {
        assertEquals(screenName, currentBackStackEntry?.destination?.route)
    }

    private fun navigateToChooseEntreeScreen() {
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.start_order)
            .performClick()
    }

    private fun navigateToChooseSideDishScreen() {
        navigateToChooseEntreeScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun navigateToChooseAccompanimentScreen() {
        navigateToChooseSideDishScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun navigateToOrderCheckoutScreen() {
        navigateToChooseAccompanimentScreen()
        composeAndroidComposeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }
}
