package com.horo.unscramble

import androidx.lifecycle.ViewModel
import com.horo.unscramble.data.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState = _uiState.asStateFlow()

    lateinit var currentPickedWord: String

    init {
        resetGame()
    }

    fun resetGame() {
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
        currentPickedWord = DataSource.wordList.random()
        return if (DataSource.usedWordList.contains(currentPickedWord)) {
            pickUnusedWord()
        } else {
            DataSource.usedWordList.add(currentPickedWord)
            currentPickedWord
        }
    }

    private fun getScrambleWord(): String {
        val tempWord = pickUnusedWord().toCharArray()
        tempWord.shuffle()

        while (String(tempWord) == currentPickedWord) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun updateGuessedWord(guessedWord: String) {
        _uiState.update {
            it.copy(
                guessedWord = guessedWord
            )
        }
    }

    fun submitButtonClicked(word: String) {
        val guessedWord = word.trim()
        if (guessedWord.isEmpty()) {
            return
        }
        if (guessedWord == currentPickedWord) {
            _uiState.update {
                it.copy(
                    currentScrambledWord = if (it.wordCount != MAX_NUMBER_OF_WORDS) getScrambleWord() else "",
                    guessedWord = "",
                    score = it.score + SCORE_INCREASE,
                    isGuessedWordWrong = false,
                    wordCount = if (it.wordCount != MAX_NUMBER_OF_WORDS) it.wordCount.inc() else it.wordCount,
                    isGameOver = it.wordCount == MAX_NUMBER_OF_WORDS
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    isGuessedWordWrong = true
                )
            }
        }
    }

    fun skipButtonClicked() {
        _uiState.update {
            it.copy(
                currentScrambledWord = getScrambleWord(),
                wordCount = it.wordCount.inc(),
                guessedWord = "",
                isGuessedWordWrong = false,
                isGameOver = it.wordCount == MAX_NUMBER_OF_WORDS
            )
        }
    }

    companion object {
        const val MAX_NUMBER_OF_WORDS = 10
        const val SCORE_INCREASE = 20
    }
}
