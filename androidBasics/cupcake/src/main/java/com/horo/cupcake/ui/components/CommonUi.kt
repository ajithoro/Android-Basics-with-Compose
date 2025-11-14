package com.horo.cupcake.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.horo.cupcake.R

@Composable
fun FormatedPriceLabel(subTotal: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.subtotal_price, subTotal),
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
    )
}
