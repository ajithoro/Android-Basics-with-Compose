package com.horo.cupcake

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.horo.cupcake.data.DataSource.flavorList
import com.horo.cupcake.data.DataSource.quantityList
import com.horo.cupcake.model.CupcakeUiState
import com.horo.cupcake.ui.SelectOptionScreen
import com.horo.cupcake.ui.StartOrderScreen
import com.horo.cupcake.ui.SummaryScreen
import com.horo.cupcake.ui.theme.CupcakeTheme

private const val TAG = "SummaryOrderScreen"

enum class CupcakeScreen(@StringRes val titleRes: Int) {
    Start(R.string.app_name),
    Flavor(R.string.choose_flavor),
    Pickup(R.string.choose_pickup_date),
    Summary(R.string.order_summary)
}

@Composable
fun CupcakeApp(
    viewModel: CupcakeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CupcakeScreen.valueOf(
        backStackEntry?.destination?.route ?: CupcakeScreen.Start.name
    )
    val canNavigationBack = navController.previousBackStackEntry != null
    val onNavigationUpButtonClick: () -> Unit = {
        when (currentScreen) {
            CupcakeScreen.Pickup -> viewModel.updatePickUpDate("")
            CupcakeScreen.Flavor -> viewModel.updateFlavor("")
            else -> {}
        }
        navController.navigateUp()
    }

    val numberOfCupcakes = pluralStringResource(
        R.plurals.cupcakes, uiState.quantity,
        uiState.quantity
    )

    val summaryItemList = listOf(
        Pair(stringResource(R.string.quantity), numberOfCupcakes),
        Pair(stringResource(R.string.flavor), uiState.flavor),
        Pair(stringResource(R.string.pickup_date), uiState.pickUpDate)
    )
    val cancelAndReset: () -> Unit = {
        viewModel.resetOrder()
        navController.popBackStack(CupcakeScreen.Start.name, inclusive = false)
    }
    val onButtonClickInStartScreen: (Int) -> Unit = {
        viewModel.updateQuantity(it)
        navController.navigate(CupcakeScreen.Flavor.name)
    }
    val onFlavorClick: (String) -> Unit = {
        viewModel.updateFlavor(it)
    }
    val onFlavorCancelClick: () -> Unit = {
        cancelAndReset()
    }
    val onFlavorNextClick: () -> Unit = {
        navController.navigate(CupcakeScreen.Pickup.name)
    }
    val onPickupDateClick: (String) -> Unit = {
        viewModel.updatePickUpDate(it)
    }
    val onPickupDateCancelClick: () -> Unit = {
        cancelAndReset()
    }
    val onPickupDateNextClick: () -> Unit = {
        navController.navigate(CupcakeScreen.Summary.name)
    }
    val context = LocalContext.current
    val onSendClickOnSummaryScreen: () -> Unit = {
        shareOrderSummary(uiState, context)
    }
    val onCancelClickOnSummaryScreen: () -> Unit = {
        cancelAndReset()
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
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                composable(route = CupcakeScreen.Start.name) {
                    StartOrderScreen(
                        buttonList = quantityList,
                        onButtonClick = onButtonClickInStartScreen,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(R.dimen.padding_medium))
                    )
                }
                composable(route = CupcakeScreen.Flavor.name) {
                    SelectOptionScreen(
                        options = flavorList.map { context.getString(it) },
                        selectedOption = uiState.flavor,
                        optionOnClick = onFlavorClick,
                        onCancelClick = onFlavorCancelClick,
                        onNextClick = onFlavorNextClick,
                        subTotal = uiState.subTotal,
                        modifier = Modifier.fillMaxHeight()
                            .padding(dimensionResource(R.dimen.padding_medium))
                    )
                }
                composable(route = CupcakeScreen.Pickup.name) {
                    SelectOptionScreen(
                        options = uiState.pickUpOptionList,
                        selectedOption = uiState.pickUpDate,
                        optionOnClick = onPickupDateClick,
                        onCancelClick = onPickupDateCancelClick,
                        onNextClick = onPickupDateNextClick,
                        subTotal = uiState.subTotal,
                        modifier = Modifier.fillMaxHeight()
                            .padding(dimensionResource(R.dimen.padding_medium))
                    )
                }
                composable(route = CupcakeScreen.Summary.name) {
                    SummaryScreen(
                        summaryList = summaryItemList,
                        onSendClick = onSendClickOnSummaryScreen,
                        onCancelClick = onCancelClickOnSummaryScreen,
                        subtotal = uiState.subTotal,
                        modifier = Modifier.fillMaxHeight()
                            .padding(dimensionResource(R.dimen.padding_medium))
                    )
                }
            }
        }
    }
}

fun shareOrderSummary(
    uiState: CupcakeUiState,
    context: Context,
) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_SUBJECT, context.resources.getString(R.string.new_cupcake_order))
        putExtra(
            Intent.EXTRA_TEXT, context.getString(
                R.string.order_details, uiState.quantity.toString(),
                uiState.flavor,
                uiState.pickUpDate,
                uiState.quantity.toString()
            )
        )
        type = "text/plain"
    }

    val chooseIntent = Intent.createChooser(
        sendIntent,
        context.resources.getString(R.string.new_cupcake_order)
    )
    try {
        context.startActivity(chooseIntent)
    } catch (e: ActivityNotFoundException) {
        Log.e(TAG, "Failed to share")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeTopAppbar(
    cupcakeScreen: CupcakeScreen,
    canNavigationBack: Boolean,
    onNavigationUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
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
                        contentDescription = stringResource(R.string.back_button)
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
        CupcakeApp(navController = rememberNavController())
    }
}
