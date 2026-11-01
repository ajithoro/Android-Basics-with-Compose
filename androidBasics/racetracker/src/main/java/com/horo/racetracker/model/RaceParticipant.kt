package com.horo.racetracker.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

class RaceParticipant(
    val name: String,
    val initialProgress: Int = 0,
    val progressIncrement: Int,
    val maxProgress: Int,
    val delayInMillis: Long = 500L,
) {

    init {
        require(progressIncrement > 0) { "progressIncrement: $progressIncrement must be > 0" }
        require(maxProgress > 0) { "maxProgress: $maxProgress must be > 0" }
    }

    var currentProgress by mutableIntStateOf(initialProgress)
        private set

    suspend fun run() {
        while (currentProgress < maxProgress) {
            delay(delayInMillis)
            currentProgress += progressIncrement
        }
    }

    fun reset() {
        currentProgress = 0
    }

}

val RaceParticipant.progressFactor: Float
    get() = currentProgress / maxProgress.toFloat()
