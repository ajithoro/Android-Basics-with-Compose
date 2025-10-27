package com.horo.unscramble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.horo.unscramble.ui.theme.UnscrambleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnscrambleTheme {
                GameScreen()
            }
        }
    }
}

@Composable
fun GameScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )

            GameLayout(currentScrambledWord = "abcd", 5, modifier = Modifier)
        }
    }
}

@Composable
fun GameLayout(currentScrambledWord: String, wordCount: Int, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth().wrapContentHeight().clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.card_elevation))
        ) {
        Column(modifier = Modifier.fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))) {

            Text(
                modifier = Modifier.clip(MaterialTheme.shapes.medium).background(MaterialTheme.colorScheme.surfaceTint)
                    .padding(horizontal = dimensionResource(R.dimen.padding_medium,)
                        , vertical = dimensionResource(R.dimen.padding_small))
                    .align(Alignment.End),
                text = stringResource(R.string.word_count, wordCount),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = currentScrambledWord,
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.instructions),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnscramblePreview() {
    UnscrambleTheme {
        GameScreen()
    }
}
