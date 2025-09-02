package com.horo.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.horo.tiptime.ui.theme.AndroidBasicsTheme
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBasicsTheme {
                TipTimeContent(modifier = Modifier)
            }
        }
    }
}

@Composable
fun TipTimeContent(modifier: Modifier = Modifier) {
    var billAmountString by remember { mutableStateOf("") }
    var tipPercentageString by remember { mutableStateOf("") }
    var isRoundUp by remember { mutableStateOf(false) }
    val billAmount = billAmountString.toDoubleOrNull() ?: 0.0
    val tipPercentage = tipPercentageString.toDoubleOrNull() ?: 0.0
    val tipAmount = calculateTip(billAmount, tipPercentage, isRoundUp)

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = modifier
                .padding(horizontal = 40.dp)
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .safeDrawingPadding()
                .statusBarsPadding(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.calculate_tip),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            EditNumberTextField(
                modifier = Modifier.padding(bottom = 32.dp),
                value = billAmountString,
                leadingIconRes = R.drawable.money,
                onValueChange = { billAmountString = it },
                label = R.string.bill_amount,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
            )
            EditNumberTextField(
                modifier = Modifier.padding(bottom = 32.dp),
                value = tipPercentageString,
                leadingIconRes = R.drawable.percent,
                onValueChange = { tipPercentageString = it },
                label = R.string.tip_percentage,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
            )
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.round_up_tip)
                )
                Switch(
                    checked = isRoundUp,
                    onCheckedChange = { isRoundUp = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.End)
                )
            }
            Text(
                text = stringResource(R.string.tip_amount, tipAmount),
                style = MaterialTheme.typography.displaySmall
            )
        }
    }

}

@Composable
fun EditNumberTextField(
    modifier: Modifier,
    value: String,
    @DrawableRes leadingIconRes: Int,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIconRes),
                contentDescription = null
            )
        },
        label = {
            Text(
                text = stringResource(label)
            )
        },
        singleLine = true,
        keyboardOptions = keyboardOptions
    )
}

@VisibleForTesting
internal fun calculateTip(
    billAmount: Double,
    tipPercentage: Double = 15.0,
    roundUp: Boolean,
): String {
    var tip = tipPercentage / 100.0 * billAmount
    if (roundUp) {
        tip = ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TipTimePreview() {
    AndroidBasicsTheme {
        TipTimeContent(modifier = Modifier)
    }
}
