package com.horo.racetracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.horo.racetracker.model.RaceParticipant
import com.horo.racetracker.model.progressFactor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun RaceTrackerApp() {
    val participant1 = remember {
        RaceParticipant(
            name = "Player 1",
            initialProgress = 0,
            progressIncrement = 1,
            maxProgress = 100,
        )
    }
    val participant2 = remember {
        RaceParticipant(
            name = "Player 2",
            initialProgress = 0,
            progressIncrement = 2,
            maxProgress = 100,
        )
    }
    var isRunning by remember { mutableStateOf(false) }

    if (isRunning) {
        LaunchedEffect(participant1, participant2) {
            coroutineScope {
                launch { participant1.run() }
                launch { participant2.run() }
            }
            isRunning = false
        }
    }

    val onStartButtonClick: (Boolean) -> Unit = {
        isRunning = it
    }

    val onResetClick = {
        participant1.reset()
        participant2.reset()
        isRunning = false
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Text(
            text = stringResource(R.string.run_a_race),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        Icon(
            painter = painterResource(R.drawable.ic_walk),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        RunningStatus(
            participateName = participant1.name,
            indicatorProgress = participant1.progressFactor,
            currentProgress = participant1.currentProgress,
            maxProgress = participant1.maxProgress,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        RunningStatus(
            participateName = participant2.name,
            indicatorProgress = participant2.progressFactor,
            currentProgress = participant2.currentProgress,
            maxProgress = participant2.maxProgress,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        RaceControls(
            onStartButtonClick = { onStartButtonClick(it) },
            isRunning = isRunning,
            onResetClick = onResetClick,
            modifier = Modifier
        )
    }
}

@Composable
fun RunningStatus(
    participateName: String,
    indicatorProgress: Float,
    currentProgress: Int,
    maxProgress: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = participateName,
            modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_small))
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            LinearProgressIndicator(
                progress = { indicatorProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        dimensionResource(
                            R.dimen
                                .progress_indicator_height
                        )
                    ),

            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.current_progress, currentProgress),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = stringResource(R.string.max_progress, maxProgress),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun RaceControls(
    onStartButtonClick: (Boolean) -> Unit,
    isRunning: Boolean,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
    ) {
        Button(
            onClick = { onStartButtonClick(!isRunning) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = if (isRunning) stringResource(R.string.pause) else stringResource(
                    R.string
                        .start
                )
            )
        }
        OutlinedButton(
            onClick = onResetClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.reset)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RunningStatusPreview() {
    RunningStatus(
        participateName = "Player One",
        indicatorProgress = 34.0f,
        currentProgress = 34,
        maxProgress = 100,
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun RaceControlsPreview() {
    RaceControls(
        onStartButtonClick = {},
        isRunning = true,
        onResetClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun RaceTrackerAppPreview() {
    RaceTrackerApp(

    )
}
