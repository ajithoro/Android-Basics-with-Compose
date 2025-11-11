package com.horo.cupcake.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.cupcake.R
import com.horo.cupcake.ui.theme.CupcakeTheme

@Composable
fun SelectOptionScreen(
    options: List<String>,
    optionOnClick: (String) -> Unit,
    subTotal: String,
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    var selectedIndex by remember { mutableIntStateOf(-1) }

    var isNextEnabled = selectedIndex in 0..options.lastIndex

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))) {
            options.forEachIndexed { index, item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                selectedIndex = index
                                optionOnClick(item)
                            }
                        )) {
                    RadioButton(
                        modifier = Modifier,
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            optionOnClick(item)
                        },
                    )
                    Text(
                        text = item,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
            HorizontalDivider(
                thickness = dimensionResource(R.dimen.divider_thickness)
            )
            Text(
                text = stringResource(R.string.subtotal_price, subTotal),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            OutlinedButton(
                onClick = onCancelClick,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.cancel)
                )
            }
            Button(
                onClick = onNextClick,
                modifier = Modifier.weight(1f),
                enabled = isNextEnabled
            ) {
                Text(
                    text = stringResource(R.string.next)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SelectOptionScreenPreview() {
    CupcakeTheme {
        SelectOptionScreen(
            listOf("Option 1", "Options 2", "Option 3"),
            optionOnClick = {},
            subTotal = "250",
            onCancelClick = {},
            onNextClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
