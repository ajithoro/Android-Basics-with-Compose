package com.horo.cupcake

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.horo.cupcake.ui.SelectOptionScreen
import com.horo.cupcake.ui.StartOrderScreen
import com.horo.cupcake.ui.SummaryScreen
import com.horo.cupcake.ui.theme.CupcakeTheme

enum class CupcakeScreen(@StringRes val titleRes: Int) {
    Start(R.string.app_name),
    Flavor(R.string.choose_flavor),
    Pickup(R.string.choose_pickup_date),
    Summary(R.string.order_summary)
}

@Composable
fun CupcakeApp(viewModel: CupcakeViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CupcakeScreen.valueOf(
        backStackEntry?.destination?.route ?: CupcakeScreen.Start.name
    )
    val canNavigationBack = navController.previousBackStackEntry != null
    val onNavigationUpButtonClick: () -> Unit = {
        navController.navigateUp()
    }
    val startScreenButtonList = listOf(
        Pair(stringResource(R.string.one_cupcake), 1),
        Pair(stringResource(R.string.six_cupcakes), 6),
        Pair(stringResource(R.string.twelve_cupcakes), 12)
    )
    val flavorList = listOf(
        stringResource(R.string.vanilla),
        stringResource(R.string.chocolate),
        stringResource(R.string.red_velvet),
        stringResource(R.string.salted_caramel),
        stringResource(R.string.coffee)
    )

    val numberOfCupcakes = pluralStringResource(
        R.plurals.cupcakes, uiState.value.quantity,
        uiState.value.quantity
    )

    val summaryItemList = listOf(
        Pair(stringResource(R.string.quantity), numberOfCupcakes),
        Pair(stringResource(R.string.flavor), uiState.value.flavor),
        Pair(stringResource(R.string.pickup_date), uiState.value.pickUpDate)
    )
    val onButtonClickInStartScreen: (Int) -> Unit = {
        viewModel.updateQuantity(it)
        navController.navigate(CupcakeScreen.Flavor.name)
    }
    val onFlavorClick: (String) -> Unit = {
        viewModel.updateFlavor(it)
    }
    val onFlavorCancelClick: () -> Unit = {
        navController.navigateUp()
    }
    val onFlavorNextClick: () -> Unit = {
        navController.navigate(CupcakeScreen.Pickup.name)
    }
    val onPickupDateClick: (String) -> Unit = {
        viewModel.updatePickUpDate(it)
    }
    val onPickupDateCancelClick: () -> Unit = {
        navController.navigateUp()
    }
    val onPickupDateNextClick: () -> Unit = {
        navController.navigate(CupcakeScreen.Summary.name)
    }
    val onSendClickOnSummaryScreen: () -> Unit = {

    }
    val onCancelClickOnSummaryScreen: () -> Unit = {
        viewModel.resetOrder()
        navController.popBackStack(CupcakeScreen.Start.name, inclusive = false)
    }
    Scaffold(topBar = {
        CupcakeTopAppbar(
            currentScreen,
            canNavigationBack,
            onNavigationUpButtonClick,
            modifier = Modifier
        )
    }) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = CupcakeScreen.Start.name,
                modifier = Modifier
            ) {
                composable(route = CupcakeScreen.Start.name) {
                    StartOrderScreen(
                        buttonList = startScreenButtonList,
                        onButtonClick = onButtonClickInStartScreen,
                        modifier = Modifier
                    )
                }
                composable(route = CupcakeScreen.Flavor.name) {
                    SelectOptionScreen(
                        options = flavorList,
                        optionOnClick = onFlavorClick,
                        onCancelClick = onFlavorCancelClick,
                        onNextClick = onFlavorNextClick,
                        subTotal = stringResource(
                            R.string.subtotal_price,
                            uiState.value.subTotal
                        ),
                    )
                }
                composable(route = CupcakeScreen.Pickup.name) {
                    SelectOptionScreen(
                        options = uiState.value.pickUpOptionList,
                        optionOnClick = onPickupDateClick,
                        onCancelClick = onPickupDateCancelClick,
                        onNextClick = onPickupDateNextClick,
                        subTotal = stringResource(
                            R.string.subtotal_price,
                            uiState.value.subTotal
                        ),
                    )
                }
                composable(route = CupcakeScreen.Summary.name) {
                    SummaryScreen(
                        summaryList = summaryItemList,
                        onSendClick = onSendClickOnSummaryScreen,
                        onCancelClick = onCancelClickOnSummaryScreen,
                        subtotal = stringResource(
                            R.string.subtotal_price,
                            uiState.value.subTotal
                        ),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeTopAppbar(
    cupcakeScreen: CupcakeScreen,
    canNavigationBack: Boolean,
    onNavigationUpButtonClick: () -> Unit,
    modifier: Modifier,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier.background(
            MaterialTheme.colorScheme.primaryContainer
        ),
        title = {
            Text(
                text = stringResource(cupcakeScreen.titleRes),
            )
        },
        navigationIcon = {
            if (canNavigationBack) {
                IconButton(onClick = onNavigationUpButtonClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CupcakeAppPreview() {
    CupcakeTheme {
        CupcakeApp()
    }
}
