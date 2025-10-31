package com.horo.unscramble.ui

import com.horo.unscramble.GameViewModel
import com.horo.unscramble.GameViewModel.Companion.MAX_NUMBER_OF_WORDS
import com.horo.unscramble.GameViewModel.Companion.SCORE_INCREASE
import com.horo.unscramble.data.DataSourceTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_Initialization_LoadFirstWord() {
        val gameUiState = viewModel.uiState.value
        val unscrambledWord = DataSourceTest.getUnscrambledWord(gameUiState.currentScrambledWord)

        assertNotEquals(unscrambledWord, gameUiState.currentScrambledWord)
        assertTrue(gameUiState.currentScrambledWord.isNotEmpty())
        assertTrue(gameUiState.wordCount == 1)
        assertTrue(gameUiState.guessedWord.isEmpty())
        assertFalse(gameUiState.isGuessedWordWrong)
        assertEquals(gameUiState.score, 0)
        assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_CheckGuessedWord_StateGetsUpdatedCorrectly() {
        val gameUiState = viewModel.uiState.value
        val unscrambledWord = DataSourceTest.getUnscrambledWord(gameUiState.currentScrambledWord)
        viewModel.currentPickedWord = unscrambledWord

        viewModel.checkGuessedWord(unscrambledWord)
        val updatedUiState = viewModel.uiState.value

        assertTrue(unscrambledWord != viewModel.currentPickedWord)
        assertTrue(updatedUiState.guessedWord.isEmpty())
        assertEquals(SCORE_INCREASE, updatedUiState.score)
        assertFalse(updatedUiState.isGuessedWordWrong)
        assertEquals(2, updatedUiState.wordCount)
        assertFalse(updatedUiState.isGameOver)
    }

    @Test
    fun gameViewModel_CheckGuessedWord_SetsGameOverFlag() {
        var expectedScore = 0
        var currentScrambledWord = "a"
        repeat(MAX_NUMBER_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            currentScrambledWord += "a"
            val unscrambledWord = DataSourceTest.getUnscrambledWord(currentScrambledWord)
            viewModel.currentPickedWord = unscrambledWord
            viewModel.checkGuessedWord(unscrambledWord)
        }

        val updatedUiState = viewModel.uiState.value

        assertTrue(updatedUiState.guessedWord.isEmpty())
        assertEquals(expectedScore, updatedUiState.score)
        assertFalse(updatedUiState.isGuessedWordWrong)
        assertEquals(MAX_NUMBER_OF_WORDS, updatedUiState.wordCount)
        assertTrue(updatedUiState.isGameOver)
    }

    @Test
    fun gameViewModel_CheckGuessedWord_SetsGuessedWordWrongFlag() {
        val gameUiState = viewModel.uiState.value
        val unscrambledWord = DataSourceTest.getUnscrambledWord(gameUiState.currentScrambledWord)
        viewModel.currentPickedWord = unscrambledWord

        viewModel.checkGuessedWord(unscrambledWord + "wrong")
        val updatedUiState = viewModel.uiState.value

        assertTrue(unscrambledWord == viewModel.currentPickedWord)
        assertEquals(gameUiState.score, updatedUiState.score)
        assertTrue(updatedUiState.isGuessedWordWrong)
        assertEquals(gameUiState.wordCount, updatedUiState.wordCount)
        assertFalse(updatedUiState.isGameOver)
    }

}
