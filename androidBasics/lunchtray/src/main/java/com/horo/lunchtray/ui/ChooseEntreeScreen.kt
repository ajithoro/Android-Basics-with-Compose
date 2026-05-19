package com.horo.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.lunchtray.R
import com.horo.lunchtray.data.DataSource
import com.horo.lunchtray.model.MenuItem

@Composable
fun ChooseEntreeScreen(
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    optionList: List<MenuItem.EntreeItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top, modifier = modifier.padding(
            dimensionResource(R.dimen.padding_medium)
        )
    ) {
        BaseMenuScreen(optionList = optionList, modifier = Modifier.fillMaxWidth())
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.padding_medium))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(
                        R.dimen.padding_medium
                    )
                )
            ) {
                OutlinedButton(
                    onClick = onCancelClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(R.string.cancel).uppercase())
                }
                Button(
                    onClick = onNextClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.next).uppercase()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseEntreeScreenPreview() {
    ChooseEntreeScreen(
        onCancelClick = {},
        onNextClick = {},
        optionList = DataSource.entreeMenuItems,
        modifier = Modifier.fillMaxSize(),
    )
}

