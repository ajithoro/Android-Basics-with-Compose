package com.horo.lunchtray.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.lunchtray.R

@Composable
fun ButtonGroup(
    cancelButtonResId: Int,
    nextButtonResId: Int,
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier,
) {
    Row(
        modifier = modifier,
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
            Text(text = stringResource(cancelButtonResId).uppercase())
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(nextButtonResId).uppercase()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonGroupPreview() {
    ButtonGroup(
        cancelButtonResId = R.string.cancel,
        nextButtonResId = R.string.next,
        onCancelClick = {},
        onNextClick = {},
        modifier = Modifier
    )
}
