package com.horo.cupcake.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.horo.cupcake.R
import com.horo.cupcake.ui.theme.CupcakeTheme

@Composable
fun SummaryScreen(summaryList: List<Pair<String, String>>, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier,
    ) {
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
            )
        )
    }
}

