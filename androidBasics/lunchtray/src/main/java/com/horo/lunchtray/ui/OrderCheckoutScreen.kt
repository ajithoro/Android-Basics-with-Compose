package com.horo.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.horo.lunchtray.R
import com.horo.lunchtray.data.DataSource
import com.horo.lunchtray.extensions.toFormattedPrice
import com.horo.lunchtray.model.MenuItem
import com.horo.lunchtray.ui.components.ButtonGroup

@Composable
fun OrderCheckoutScreen(
    onCancelClick: () -> Unit,
    onSubmitClick: () -> Unit,
    selectedEntreeItem: MenuItem.EntreeItem?,
    selectedSideDishItem: MenuItem.SideDishItem?,
    selectedAccompanimentItem: MenuItem.AccompanimentItem?,
    subtotal: Double,
    tax: Double,
    total: Double,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))) {
        Column(
            verticalArrangement =
                Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(R.string.order_summary),
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = selectedEntreeItem?.name ?: "")
                Text(
                    text = selectedEntreeItem?.price?.toFormattedPrice() ?: 0.0.toFormattedPrice(),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = selectedSideDishItem?.name ?: "")
                Text(
                    text = selectedSideDishItem?.price?.toFormattedPrice()
                        ?: 0.0.toFormattedPrice(),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = selectedAccompanimentItem?.name ?: "")
                Text(
                    text = selectedAccompanimentItem?.price?.toFormattedPrice()
                        ?: 0.0.toFormattedPrice(),
                )
            }
            HorizontalDivider(thickness = dimensionResource(R.dimen.divider_thickness))
            Text(
                text = stringResource(R.string.subtotal, subtotal.toFormattedPrice()),
                modifier = Modifier.align(alignment = Alignment.End)
            )
            Text(
                text = stringResource(R.string.tax, tax.toFormattedPrice()),
                modifier = Modifier.align(alignment = Alignment.End)
            )
            Text(
                text = stringResource(R.string.total, total.toFormattedPrice()),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(alignment = Alignment.End)
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        }
        ButtonGroup(
            cancelButtonResId = R.string.cancel,
            nextButtonResId = R.string.submit,
            onCancelClick = onCancelClick,
            onNextClick = onSubmitClick,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderCheckoutScreenPreview() {
    OrderCheckoutScreen(
        onCancelClick = {},
        onSubmitClick = {},
        selectedEntreeItem = DataSource.entreeMenuItems.first(),
        selectedSideDishItem = DataSource.sideDishMenuItems.first(),
        selectedAccompanimentItem = DataSource.accompanimentMenuItems.first(),
        subtotal = 9.0,
        tax = 34.0,
        total = 100.0
    )
}

