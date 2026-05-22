package com.horo.lunchtray.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.lunchtray.R
import com.horo.lunchtray.data.DataSource
import com.horo.lunchtray.model.MenuItem
import com.horo.lunchtray.ui.components.BaseMenuScreen
import com.horo.lunchtray.ui.components.ButtonGroup

@Composable
fun ChooseSideDishScreen(
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    optionList: List<MenuItem.SideDishItem>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))) {
        BaseMenuScreen(
            optionList = optionList, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        ButtonGroup(
            R.string.cancel,
            R.string.next,
            onCancelClick,
            onNextClick,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseSideDishScreenPreview() {
    ChooseSideDishScreen(
        onCancelClick = {},
        onNextClick = {},
        optionList = DataSource.sideDishMenuItems,
        modifier = Modifier,
    )
}


