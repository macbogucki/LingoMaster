package com.example.lingomaster.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.ViewModel
import com.example.lingomaster.data.allWords
import com.example.lingomaster.data.englishToPolishMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var usedWords: MutableSet<String> = mutableSetOf()
    private var possibleAnswers: MutableSet<String> = mutableSetOf()
    private var correctAnswer = ""
    private var isUserHasLifes = true
    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    fun DrawCorrectAnswer() {
        correctAnswer = allWords.random()
        if (usedWords.contains(correctAnswer))
        {
            return DrawCorrectAnswer()
        }
        else{
            usedWords.add(correctAnswer)
        }
    }

    fun mapToPolish(correctAnswer: String): String {
        return englishToPolishMap[correctAnswer] ?: "Brak tłumaczenia"
    }

    fun DrawSingleAnswer(){
        val answer = allWords.random()
        if (possibleAnswers.contains(answer) || answer == correctAnswer)
        {
            return DrawSingleAnswer()
        }
        else{
            possibleAnswers.add(answer)
        }
    }

    fun DrawPossibleAnswers(){
        for (i in 0..2)
        {
            DrawSingleAnswer()
        }
    }

    fun cleanPossibleAnswers(){
        possibleAnswers.clear()
    }

    fun resetGame(){
        isUserHasLifes = true
        usedWords.clear()
        cleanPossibleAnswers()
        DrawCorrectAnswer()
        DrawPossibleAnswers()
        possibleAnswers.add(correctAnswer)
        shuffleAnswers()
        _uiState.value = GameUiState(
            currentCorrectWord = correctAnswer,
            currentCorrectWordPolish = mapToPolish(correctAnswer),
            currentPossibleAnswers = possibleAnswers)
    }

    fun shuffleAnswers() {
        val shuffledAnswers = possibleAnswers.shuffled()
        possibleAnswers = shuffledAnswers.toSet() as MutableSet<String>
    }

    fun CheckUserAnswer(userGuess: String){
        var updatedLifes = _uiState.value.userLives
        if (userGuess == correctAnswer)
        {
            val updatedScore = _uiState.value.score.plus(1)
            _uiState.update { currentState -> currentState.copy(score = updatedScore) }

        }
        else{
            updatedLifes = _uiState.value.userLives.minus(1)
            _uiState.update { currentState -> currentState.copy(isGuessedWordWrong= true, userLives = updatedLifes) }
            if (updatedLifes == 0){
                isUserHasLifes = false
            }
        }
        _uiState.update { currentState -> currentState.copy(isShowDialogAlert = true) }

    }

    fun updateGameState(){
        if (usedWords.size == 20 || !isUserHasLifes){
            //Last round in the game
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    isShowDialogAlert = false,
                    isGameOver = true
                )
            }
        }
        else {
            cleanPossibleAnswers()
            DrawPossibleAnswers()
            DrawCorrectAnswer()
            possibleAnswers.add(correctAnswer)
            shuffleAnswers()
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    isShowDialogAlert = false,
                    currentCorrectWord = correctAnswer,
                    currentCorrectWordPolish = mapToPolish(correctAnswer),
                    currentPossibleAnswers = possibleAnswers,
                    currentWordCount = currentState.currentWordCount.inc()
                )
            }
        }
    }
}