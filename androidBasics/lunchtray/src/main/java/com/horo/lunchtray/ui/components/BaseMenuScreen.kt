package com.horo.lunchtray.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.lunchtray.R
import com.horo.lunchtray.data.DataSource
import com.horo.lunchtray.model.MenuItem

@Composable
fun BaseMenuScreen(
    optionList: List<MenuItem>,
    modifier: Modifier,
) {

    var selectedItemName by rememberSaveable { mutableStateOf("") }

    Column {
        optionList.forEach { item ->
            MenuItem(
                menuItem = item,
                selectedItemName = selectedItemName,
                onItemClick = { currentSelectedItemName ->
                    selectedItemName = currentSelectedItemName
                },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        }
    }
}

@Composable
fun MenuItem(
    menuItem: MenuItem,
    selectedItemName: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .selectable(
                selected = selectedItemName == menuItem.name,
                onClick = { onItemClick(menuItem.name) }
            ),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedItemName == menuItem.name,
            onClick = { onItemClick(menuItem.name) },
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.padding_small)
            )
        ) {
            Text(
                text = menuItem.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = menuItem.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = menuItem.getFormattedPrice(),
                style = MaterialTheme.typography.bodyMedium
            )
            HorizontalDivider(
                thickness = dimensionResource(R.dimen.divider_thickness),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseMenuScreenPreview() {
    BaseMenuScreen(
        optionList = DataSource.entreeMenuItems,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    MenuItem(
        menuItem = MenuItem.EntreeItem(
            name = "Cauliflower",
            description = "Whole cauliflower, brined, roasted, and deep fried",
            price = 7.00,
        ),
        selectedItemName = "Cauliflower",
        onItemClick = {},
        modifier = Modifier
    )
}
