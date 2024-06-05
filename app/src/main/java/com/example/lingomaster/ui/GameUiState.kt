package com.example.lingomaster.ui

data class GameUiState (
    val score: Int = 0,
    val currentCorrectWord: String = "",
    val currentCorrectWordPolish: String = "",
    val userChosenWord: String = "",
    val isGameOver: Boolean = false,
    val isGuessedWordWrong: Boolean = false,
    val currentWordCount: Int = 1,
    val userLives: Int = 3,
    val currentPossibleAnswers: Set<String> = emptySet(),
    val isShowDialogAlert: Boolean = false
)