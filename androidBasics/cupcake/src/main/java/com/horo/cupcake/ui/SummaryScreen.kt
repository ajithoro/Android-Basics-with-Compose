package com.horo.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.horo.cupcake.R
import com.horo.cupcake.ui.theme.CupcakeTheme

@Composable
fun SummaryScreen(
    summaryList: List<Pair<String, String>>,
    onSendClick: () -> Unit,
    onCancelClick: () -> Unit,
    subtotal: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
    ) {
        Column {
            for (item in summaryList) {
                Column {
                    Text(
                        text = item.first,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = item.second,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    HorizontalDivider(
                        modifier = Modifier,
                        thickness = dimensionResource(R.dimen.divider_thickness)
                    )
                }
            }
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        vertical = dimensionResource(
                            R.dimen.padding_medium
                        )
                    ),
                text = stringResource(R.string.subtotal_price, subtotal),
                style = MaterialTheme.typography.headlineMedium
            )


        }
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_medium))
        ) {
            Button(
                onClick = onSendClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.send)
                )
            }
            OutlinedButton(
                onClick = onCancelClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.cancel)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SummaryScreenPreview() {
    CupcakeTheme {
        SummaryScreen(
            listOf(
                Pair("QUANTITY", "1 cupcake"),
                Pair("FLAVOR", "Coffee"),
                Pair("PICKUP DATE", "Sun Nov 9")
            ),
            onSendClick = { },
            onCancelClick = { },
            subtotal = "",
        )
    }
}
