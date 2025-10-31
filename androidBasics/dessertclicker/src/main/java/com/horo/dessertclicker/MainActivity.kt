package com.horo.dessertclicker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.dessertclicker.data.DataSource
import com.horo.dessertclicker.model.Dessert
import com.horo.dessertclicker.ui.theme.AndroidBasicsTheme


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        enableEdgeToEdge()
        setContent {
            AndroidBasicsTheme {
                DessertScreen(DataSource.dessertList)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }
}

@Composable
fun DessertScreen(dessertList: List<Dessert>) {
    var imageRes by rememberSaveable { mutableIntStateOf(dessertList.first().imageRes) }
    var dessertSold by rememberSaveable { mutableIntStateOf(0) }
    var currentPrice by rememberSaveable { mutableIntStateOf(dessertList.first().price) }
    var revenue by rememberSaveable { mutableIntStateOf(0) }
    val intentContext = LocalContext.current
    val onSharedClicked: () -> Unit = { shareDessertSoldInfo(intentContext, dessertSold, revenue) }
    val onImageClick: () -> Unit = {
        dessertSold++
        revenue += currentPrice
        val dessertToShow = getDessertToShow(dessertList, dessertSold)
        imageRes = dessertToShow.imageRes
        currentPrice = dessertToShow.price
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .statusBarsPadding(),
            topBar = {
                val layoutDirection = LocalLayoutDirection.current
                DessertTopBar(
                    onShareClicked = onSharedClicked, modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(
                            start = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateStartPadding(layoutDirection),
                            end = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateEndPadding(layoutDirection)
                        )
                )
            }) {
            Surface(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(paddingValues = it)
            ) {
                DessertScreenContent(
                    imageRes = imageRes,
                    bakerSold = dessertSold,
                    revenue = revenue,
                    onImageClick = onImageClick
                )
            }
        }
    }
}

@SuppressLint("QueryPermissionsNeeded")
fun shareDessertSoldInfo(intentContext: Context, dessertSold: Int, revenue: Int) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            intentContext.resources.getString(R.string.share_text, dessertSold, revenue)
        )
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Dessert sold info")
    if (sendIntent.resolveActivity(intentContext.packageManager) != null) {
        intentContext.startActivity(shareIntent)
    }
}

fun getDessertToShow(dessertList: List<Dessert>, backerSold: Int): Dessert {
    var dessertToShow = dessertList.first()
    for (dessert in dessertList) {
        if (backerSold >= dessert.startAmountProduction) {
            dessertToShow = dessert
        } else {
            break
        }
    }

    return dessertToShow
}

@Composable
private fun DessertScreenContent(
    imageRes: Int,
    bakerSold: Int,
    revenue: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.bakery_back),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .align(Alignment.Center)
                        .clickable(onClick = onImageClick),
                    painter = painterResource(imageRes),
                    contentDescription = null,
                )
            }
            BackerDetails(
                modifier = Modifier,
                backerSold = bakerSold,
                revenue = revenue
            )
        }
    }
}

@Composable
fun BackerDetails(backerSold: Int, revenue: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.dessert_sold),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = backerSold.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.total_revenue),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "$$revenue",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
fun DessertTopBar(onShareClicked: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        IconButton(onClick = onShareClicked) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(R.string.share),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DessertScreenPreview() {
    AndroidBasicsTheme {
        DessertScreen(DataSource.dessertList)
    }
}
