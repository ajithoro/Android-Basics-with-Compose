package com.horo.cupcake.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.cupcake.R
import com.horo.cupcake.ui.theme.CupcakeTheme

@Composable
fun SelectOptionScreen(
    options: List<String>, optionOnClick: (String) -> Unit,
    selectedIndex: Int, modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {
        options.forEachIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable(
                        onClick = { optionOnClick(item) }
                    )) {
                RadioButton(
                    modifier = Modifier,
                    selected = selectedIndex == index,
                    onClick = {
                        optionOnClick(item)
                    },
                )
                Text(
                    text = item,
                    style = MaterialTheme.typography.headlineSmall
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
            selectedIndex = -1,
            modifier = Modifier.fillMaxSize()
        )
    }
}
