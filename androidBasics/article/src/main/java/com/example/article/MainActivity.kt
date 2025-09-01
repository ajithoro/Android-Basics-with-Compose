package com.example.article

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                Scaffold { innerPadding ->
                    innerPadding
                    ArticleContent(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun ArticleContent(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.weight(1f).fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Quadrant(
                modifier = Modifier.weight(1f),
                title = "Text composable",
                desc = "Displays text and follows the recommended Material Design guidelines.",
                color = Color1,
            )
            Quadrant(
                modifier = Modifier.weight(1f),
                title = "Image composable",
                desc = "Creates a composable that lays out and draws a given Painter class object.",
                color = Color2,
            )
        }
        Row(modifier = Modifier.weight(1f).fillMaxSize()) {
            Quadrant(
                modifier = Modifier.weight(1f),
                title = "Image composable  3",
                desc = "Creates a composable that lays out and draws a given Painter class object.",
                color = Color3,
            )
            Quadrant(
                modifier = Modifier.weight(1f),
                title = "Image composable",
                desc = "Creates a composable that lays out and draws a given Painter class object.",
                color = Color4,
            )
        }
    }
}

@Composable
fun Quadrant(modifier: Modifier, title: String, desc: String, color: Color) {
    Column(modifier = modifier.fillMaxSize().background(color = color),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = desc,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun ArticleContentPreview() {
    ArticleTheme {
        ArticleContent(modifier = Modifier)
    }
}
