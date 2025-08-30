package com.example.article

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.article.ui.theme.ArticleTheme
import com.example.article.ui.theme.Color1
import com.example.article.ui.theme.Color2
import com.example.article.ui.theme.Color3
import com.example.article.ui.theme.Color4

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArticleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Articles(
                        modifier = Modifier
                            .background(
                                color = Color.Red
                            )
                            .padding(innerPadding)
                    )
//                    Greeting(
//                        title = "Android", modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    title: String,
    desc1: String,
    desc2: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier,
        verticalArrangement = Arrangement.Top) {
        Image(
            painter = painterResource(R.drawable.bg_compose_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = desc1,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = desc2,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun FinishScreen(message1: String,
                 message2: String,
                 modifier: Modifier = Modifier) {
    Column(modifier = modifier,
        verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(R.drawable.ic_task_completed),
            contentDescription = null,
            modifier = Modifier.align(
                alignment = Alignment.CenterHorizontally
            )
        )
        Text(
            text = message1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(
                    Alignment.CenterHorizontally
                )
                .padding(
                    top = 24.dp,
                    bottom = 8.dp
                )
        )
        Text(
            text = message2,
            fontSize = 16.sp,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )
    }
}

@Composable
fun Articles(
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxSize(),
        ) {
        Row(modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Quadrant(
                title = "Text composable",
                desc = "Displays text and follows the recommended Material Design guidelines.",
                modifier = Modifier
                    .background(
                        color = Color1
                    )
                    .weight(1f)
                    .fillMaxSize()

            )
            Quadrant(
                title = "Image composable",
                desc = "Creates a composable that lays out and draws a given Painter class object.",
                modifier = Modifier
                    .background(
                        color = Color2
                    )
                    .weight(1f)
                    .fillMaxSize()
            )
        }
        Row(modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Quadrant(
                title = "Text composable",
                desc = "Displays text and follows the recommended Material Design guidelines.",
                modifier = Modifier
                    .background(
                        color = Color3
                    )
                    .weight(1f)
                    .fillMaxSize()
            )
            Quadrant(
                title = "Image composable",
                desc = "Creates a composable that lays out and draws a given Painter class object.",
                modifier = Modifier
                    .background(
                        color = Color4
                    )
                    .weight(1f)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun Quadrant(title: String,
             desc: String,
             modifier: Modifier = Modifier) {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(bottom = 16.dp)
            )
            Text(
                text = desc,
                textAlign = TextAlign.Justify,
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                )
            )
        }
}

@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
fun GreetingPreview() {
    ArticleTheme {
//        Articles(
//            modifier = Modifier.background(
//                color = Color.Red
//            )
//        )
//        Quadrant(
//            title = "Title",
//            desc = "desc",
//            modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    color = Color1
//                )
//        )

        Greeting(title = stringResource(R.string.text_title),
            desc1 = stringResource(R.string.text_desc1),
            desc2 = stringResource(R.string.text_desc2)
        )

//        FinishScreen(
//            message1 = stringResource(R.string.text_all_task),
//            message2 = stringResource(R.string.text_nice_work),
//            modifier = Modifier.fillMaxSize()
//        )
    }
}
