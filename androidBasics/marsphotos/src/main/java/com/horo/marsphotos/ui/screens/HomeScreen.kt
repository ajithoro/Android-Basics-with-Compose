package com.horo.marsphotos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.horo.marsphotos.R
import com.horo.marsphotos.ui.theme.AndroidBasicsTheme

@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is MarsUiState.Success -> SuccessScreen(modifier = Modifier.fillMaxSize())
        is MarsUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun SuccessScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.placeholder_success_text))
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(R.drawable.ic_connection_error),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.loading)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AndroidBasicsTheme {
        LoadingScreen(modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    AndroidBasicsTheme {
        SuccessScreen(modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AndroidBasicsTheme {
        ErrorScreen(modifier = Modifier.fillMaxSize())
    }
}
