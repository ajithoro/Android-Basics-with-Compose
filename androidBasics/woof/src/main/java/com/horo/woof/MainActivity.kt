package com.horo.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.horo.woof.data.DataSource
import com.horo.woof.model.Dog
import com.horo.woof.ui.theme.AndroidBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBasicsTheme {
                DogApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogApp(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = { DogTopAppBar(scrollBehavior) },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        Surface(modifier = Modifier.padding(it)) {
            DogList(modifier = Modifier)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Image(
                    painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun DogList(modifier: Modifier = Modifier) {
    val dogList = DataSource.dogList
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        items(dogList) {
            DogItem(it)
        }
    }
}

@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Image(
                painter = painterResource(dog.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size))
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )
            DogInfo(
                dog = dog,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Composable
fun DogInfo(dog: Dog, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(dog.name),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = dog.age.toString(),
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DogAppPreview() {
    AndroidBasicsTheme {
        DogApp()
    }
}

@Preview(showBackground = true)
@Composable
fun DogAppDarkThemePreview() {
    AndroidBasicsTheme(darkTheme = true) {
        DogApp()
    }
}
