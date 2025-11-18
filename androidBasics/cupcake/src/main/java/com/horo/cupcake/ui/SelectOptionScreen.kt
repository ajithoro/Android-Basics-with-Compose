package com.horo.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.cupcake.R
import com.horo.cupcake.ui.components.FormatedPriceLabel
import com.horo.cupcake.ui.theme.CupcakeTheme

@Composable
fun SelectOptionScreen(
    options: List<String>,
    selectedOption: String,
    optionOnClick: (String) -> Unit,
    subTotal: String,
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    var selectedIndex by rememberSaveable { mutableIntStateOf(options.indexOf(selectedOption)) }
    val isNextEnabled = selectedIndex in 0..options.lastIndex

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))) {
            options.forEachIndexed { index, item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .selectable(
                            selected = selectedIndex == index,
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
                    )
                }
            }
            HorizontalDivider(
                thickness = dimensionResource(R.dimen.divider_thickness)
            )
            FormatedPriceLabel(
                subTotal, Modifier
                    .align(Alignment.End)
                    .padding(vertical = dimensionResource(R.dimen.padding_medium))
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
            selectedOption = "",
            optionOnClick = {},
            subTotal = "250",
            onCancelClick = {},
            onNextClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
