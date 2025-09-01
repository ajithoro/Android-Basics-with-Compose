package com.horo.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.horo.lemonade.ui.theme.AndroidBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBasicsTheme {
               LemonadeView(modifier = Modifier)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeView(modifier: Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.lemonade),
                        fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            LemonadeViewContent(modifier = modifier)
        }
    }
}

@Composable
fun LemonadeViewContent(modifier: Modifier) {
    var contentState by remember { mutableIntStateOf(1) }
    var squeezeState by remember { mutableIntStateOf(0) }
    when(contentState) {
        1 ->  TextAndImage(
            modifier = modifier,
            onButtonClick = {
                contentState = 2
                squeezeState = (2..4).random()
            },
            imageRes = R.drawable.lemon_select,
            stringRes = R.string.lemon_select,
        )
        2 ->  TextAndImage(
            modifier = modifier,
            onButtonClick = {
                squeezeState--
                if (squeezeState == 0) {
                    contentState = 3
                }
            },
            imageRes = R.drawable.lemon_squeeze,
            stringRes = R.string.lemon_squeeze,
        )
        3 ->  TextAndImage(
            modifier = modifier,
            onButtonClick = {
                contentState = 4
            },
            imageRes = R.drawable.lemon_drink,
            stringRes = R.string.lemon_drink,
        )
        4 ->  TextAndImage(
            modifier = modifier,
            onButtonClick = {
                contentState = 1
            },
            imageRes = R.drawable.lemon_restart,
            stringRes = R.string.lemon_restart,
        )
    }

}

@Composable
fun TextAndImage(
    modifier: Modifier,
    onButtonClick: () -> Unit,
    @DrawableRes imageRes: Int,
    @StringRes stringRes: Int
) {
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onButtonClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius))) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(stringRes)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadePreview() {
    AndroidBasicsTheme {
        LemonadeView(modifier = Modifier)
    }
}
