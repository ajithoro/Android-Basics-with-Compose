package com.horo.cupcake.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.cupcake.R
import com.horo.cupcake.ui.theme.CupcakeTheme

@Composable
fun StartOrderScreen(
    buttonList: List<Pair<String, Int>>,
    onButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.cupcake),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.order_cupcakes),
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.padding_medium)
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (item in buttonList) {
                Button(
                    onClick = { onButtonClick(item.second) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                ) {
                    Text(
                        text = item.first,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartOrderScreenPreview() {
    CupcakeTheme {
        StartOrderScreen(
            buttonList = listOf(
                Pair("One Cupcake", 1), Pair(
                    "Six Cupcake", 6
                ), Pair("Twelve Cupcake", 12)
            ),
            onButtonClick = {}
        )
    }
}
