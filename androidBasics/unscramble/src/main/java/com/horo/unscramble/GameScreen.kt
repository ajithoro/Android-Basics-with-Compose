package com.horo.unscramble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.horo.unscramble.ui.theme.UnscrambleTheme
import kotlin.math.roundToInt

@Composable
fun GameScreen() {
    val gameViewModel: GameViewModel = viewModel()
    val uiState by gameViewModel.uiState.collectAsState()
    val onGuessedWordChanged: (String) -> Unit = { }
    val submitButtonClicked = { }
    val skipButtonClicked = { }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .safeDrawingPadding()
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            GameLayout(
                currentScrambledWord = uiState.currentScrambledWord,
                wordCount = uiState.wordCount,
                guessedWord = uiState.guessedWord,
                onValueChanged = onGuessedWordChanged,
                isGuessedWordWrong = uiState.isGuessedWordWrong,
                modifier = Modifier,
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = submitButtonClicked
                ) {
                    Text(
                        text = stringResource(R.string.submit),
                        fontSize = dimensionResource(R.dimen.button_text_size).value.roundToInt().sp
                    )
                }

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = skipButtonClicked
                ) {
                    Text(
                        text = stringResource(R.string.skip),
                        fontSize = dimensionResource(R.dimen.button_text_size).value.roundToInt().sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            GameStatus(score = 5, modifier = Modifier)
        }
    }
}

@Composable
fun GameStatus(
    score: Int,
    modifier: Modifier,
) {
    Card(modifier = modifier) {
        Text(
            text = stringResource(R.string.score, score),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
        )
    }
}

@Composable
fun GameLayout(
    currentScrambledWord: String,
    wordCount: Int,
    guessedWord: String,
    isGuessedWordWrong: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.card_elevation))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {

            Text(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceTint)
                    .padding(
                        horizontal = dimensionResource(R.dimen.padding_small),
                        vertical = dimensionResource(R.dimen.padding_extra_small)
                    )
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
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                value = guessedWord,
                onValueChange = onValueChanged,
                isError = isGuessedWordWrong,
                label = {
                    if (isGuessedWordWrong) {
                        Text(
                            text = stringResource(R.string.wrong_guess),
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.enter_your_word)
                        )
                    }
                }
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
