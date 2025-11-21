@file:OptIn(ExperimentalMaterial3Api::class)

package com.horo.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.horo.lunchtray.ui.ChooseAccompanimentScreen
import com.horo.lunchtray.ui.ChooseEntreeScreen
import com.horo.lunchtray.ui.ChooseSideDishScreen
import com.horo.lunchtray.ui.OrderCheckoutScreen
import com.horo.lunchtray.ui.StartOrderScreen

enum class LunchTrayScreen(@StringRes val screenNameRes: Int) {
    StartOrder(R.string.app_name),
    ChooseEntree(R.string.choose_entree),
    ChooseSideDish(R.string.choose_side_dish),
    ChooseAccompaniment(R.string.choose_accompaniment),
    OrderCheckout(R.string.order_checkout)
}

@Composable
fun LunchTrayScreen(navController: NavHostController = rememberNavController()) {
    val onNavigationUpClick: () -> Unit = {
        navController.navigateUp()
    }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val screenName = LunchTrayScreen.valueOf(
        currentBackStackEntry?.destination?.route ?: LunchTrayScreen.StartOrder.name
    )
    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(screenName.screenNameRes)
                    )
                },
                navigationIcon = {
                    if (canNavigateBack) {
                        IconButton(onClick = onNavigationUpClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = LunchTrayScreen.StartOrder.name
            ) {
                composable(route = LunchTrayScreen.StartOrder.name) {
                    StartOrderScreen(
                        onStartOrderButtonClick = {
                            navController.navigate(LunchTrayScreen.ChooseEntree.name)
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable(route = LunchTrayScreen.ChooseEntree.name) {
                    ChooseEntreeScreen(
                        onCancelClick = {
                            navController.popBackStack(
                                LunchTrayScreen.StartOrder.name,
                                false
                            )
                        },
                        onNextClick = {
                            navController.navigate(LunchTrayScreen.ChooseSideDish.name)
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable(route = LunchTrayScreen.ChooseSideDish.name) {
                    ChooseSideDishScreen(
                        onCancelClick = {
                            navController.popBackStack(
                                LunchTrayScreen.StartOrder.name,
                                false
                            )
                        },
                        onNextClick = {
                            navController.navigate(LunchTrayScreen.ChooseAccompaniment.name)
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable(route = LunchTrayScreen.ChooseAccompaniment.name) {
                    ChooseAccompanimentScreen(
                        onCancelClick = {
                            navController.popBackStack(
                                LunchTrayScreen.StartOrder.name,
                                false
                            )
                        },
                        onNextClick = {
                            navController.navigate(LunchTrayScreen.OrderCheckout.name)
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable(route = LunchTrayScreen.OrderCheckout.name) {
                    OrderCheckoutScreen(
                        onCancelClick = {
                            navController.popBackStack(
                                LunchTrayScreen.StartOrder.name,
                                false
                            )
                        },
                        onSubmitClick = {
                            navController.popBackStack(
                                LunchTrayScreen.StartOrder.name,
                                false
                            )
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
