@file:OptIn(ExperimentalMaterial3Api::class)

package com.horo.marsphotos.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.horo.marsphotos.R
import com.horo.marsphotos.ui.screens.HomeScreen
import com.horo.marsphotos.ui.screens.MarsViewModel
import com.horo.marsphotos.ui.theme.AndroidBasicsTheme

@Composable
fun MarsPhotosApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MarsTopAppBar(scrollBehavior) }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            val marsViewModel: MarsViewModel = viewModel()
            HomeScreen(marsViewModel.marsUiState, it, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun MarsTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(R.string.app_bar_title),
            style = MaterialTheme.typography.headlineSmall
        )
    }, scrollBehavior = scrollBehavior, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun MarsPhotosAppPreview() {
    AndroidBasicsTheme {
        MarsPhotosApp()
    }
}
