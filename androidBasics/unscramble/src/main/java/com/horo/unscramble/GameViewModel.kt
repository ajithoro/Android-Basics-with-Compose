package com.horo.unscramble

import androidx.lifecycle.ViewModel
import com.horo.unscramble.data.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())

    val uiState = _uiState.asStateFlow()

    lateinit var currentWord: String

    init {
        resetGame()
    }

    private fun resetGame() {
        _uiState.value = GameUiState()
        DataSource.usedWordList.clear()
        val currentScrambledWord = getScrambleWord()
        _uiState.update { it ->
            it.copy(
                currentScrambledWord = currentScrambledWord
            )
        }
    }

private fun pickUnusedWord(): String {
        currentWord = DataSource.wordList.random()
        return if (DataSource.usedWordList.contains(currentWord)) {
            pickUnusedWord()
        } else {
            currentWord
        }
    }

private fun getScrambleWord(): String {
        val tempWord = pickUnusedWord().toCharArray()
        tempWord.shuffle()

        while (String(tempWord) == currentWord) {
            tempWord.shuffle()
        }
        DataSource.usedWordList.add(currentWord)
        return String(tempWord)
    }
}
