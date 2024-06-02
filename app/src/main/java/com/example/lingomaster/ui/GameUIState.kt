package com.example.lingomaster.ui

data class GameUIState (
    val score: Int,
    val currentCorrectWord: String,
    val userChosenWord: String,
    val isGameOver: Boolean,
    val isGuessedWordRight: Boolean,
    val currentWordCount: Int,
    val userLives: Int
)