package com.horo.unscramble

data class GameUiState(
    val currentScrambledWord: String = "",
    val wordCount: Int = 1,
    val guessedWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val isGameOver: Boolean = false
)
