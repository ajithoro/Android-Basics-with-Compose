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
fun ChooseSideDishScreen(
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    optionList: List<MenuItem.SideDishItem>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        BaseMenuScreen(
            optionList = optionList, modifier = Modifier.padding(
                end = dimensionResource(
                    R
                        .dimen.padding_medium
                )
            )
        )
        Column(modifier = Modifier) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
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
fun ChooseSideDishScreenPreview() {
    ChooseSideDishScreen(
        onCancelClick = {},
        onNextClick = {},
        optionList = DataSource.sideDishMenuItems,
        modifier = Modifier.fillMaxSize(),
    )
}


