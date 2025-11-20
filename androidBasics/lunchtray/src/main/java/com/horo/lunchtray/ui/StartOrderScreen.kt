package com.horo.lunchtray.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.lunchtray.R

@Composable
fun StartOrderScreen(
    onStartOrderButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onStartOrderButtonClick,
            modifier = Modifier.widthIn(min = dimensionResource(R.dimen.min_button_size))
                .padding(dimensionResource(R.dimen.padding_medium))) {
            Text(
                text = stringResource(R.string.start_order)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartOrderScreenPreview() {
    StartOrderScreen(onStartOrderButtonClick = {}, modifier = Modifier.fillMaxSize())
}
