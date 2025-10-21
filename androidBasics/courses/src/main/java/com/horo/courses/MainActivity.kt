package com.horo.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.horo.courses.data.DataSource
import com.horo.courses.model.Topic
import com.horo.courses.ui.theme.AndroidBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBasicsTheme {
                TopicsScreen()
            }
        }
    }
}

@Composable
fun TopicsScreen() {
    Surface(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        TopicsGrid(topics = DataSource.topics)
    }
}

@Composable
fun TopicsGrid(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        items(topics) {
            Topic(it)
        }
    }
}

@Composable
fun Topic(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier) {
        Row {
            Box {
                Image(
                    painter = painterResource(topic.imgRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(68.dp)
                        .aspectRatio(1.0f),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                    top = dimensionResource(R.dimen.padding_medium)
                )
            ) {
                Text(
                    text = stringResource(topic.name),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null
                    )
                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TopicPreview() {
    AndroidBasicsTheme {
        TopicsScreen()
    }
}

