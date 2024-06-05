package com.example.lingomaster.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.ViewModel
import com.example.lingomaster.data.englishWords
import com.example.lingomaster.data.englishToPolishMap
import com.example.lingomaster.data.germanToPolishMap
import com.example.lingomaster.data.germanWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.intellij.lang.annotations.Language

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var usedWords: MutableSet<String> = mutableSetOf()
    private var possibleAnswers: MutableSet<String> = mutableSetOf()
    private var correctAnswer = ""
    private var isUserHasLifes = true
    private var currentSetOfWord: MutableSet<String> = mutableSetOf()
    private var currentLanguage = "angielski"

    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    fun setNewLanguage(newLanguage: String){
        currentLanguage = newLanguage
        _uiState.update { currentState -> currentState.copy(currentLanguage = currentLanguage) }
    }
    fun setLanguage(){
        _uiState.update { currentState -> currentState.copy(currentLanguage = currentLanguage) }
    }
    fun selectSetOfWords(){
        if (_uiState.value.currentLanguage == "angielski"){
            currentSetOfWord = englishWords as MutableSet<String>
        }
        if (_uiState.value.currentLanguage == "niemiecki"){
            currentSetOfWord = germanWords as MutableSet<String>
        }
    }

    fun DrawCorrectAnswer() {
        correctAnswer = currentSetOfWord.random()
        if (usedWords.contains(correctAnswer))
        {
            return DrawCorrectAnswer()
        }
        else{
            usedWords.add(correctAnswer)
        }
    }

    fun mapWord(correctAnswer: String): String{
        if (currentLanguage == "angielski"){
            return englishMapToPolish(correctAnswer)
        }
        if (currentLanguage == "niemiecki"){
            return germanMapToPolish(correctAnswer)
        }
        return "Brak tłumaczenia all"
    }

    fun englishMapToPolish(correctAnswer: String): String {
        return englishToPolishMap[correctAnswer] ?: "Brak tłumaczenia ang"
    }

    fun germanMapToPolish(correctAnswer: String): String {
        return germanToPolishMap[correctAnswer] ?: "Brak tłumaczenia de"
    }

    fun DrawSingleAnswer(){
        val answer = currentSetOfWord.random()
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
        selectSetOfWords()
        isUserHasLifes = true
        usedWords.clear()
        cleanPossibleAnswers()
        DrawCorrectAnswer()
        DrawPossibleAnswers()
        possibleAnswers.add(correctAnswer)
        shuffleAnswers()
        _uiState.value = GameUiState(
            currentCorrectWord = correctAnswer,
            currentCorrectWordPolish = mapWord(correctAnswer),
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
                    isGameOver = true,
                    currentLanguage = currentLanguage
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
                    currentCorrectWordPolish = mapWord(correctAnswer),
                    currentPossibleAnswers = possibleAnswers,
                    currentWordCount = currentState.currentWordCount.inc(),
                    currentLanguage = currentLanguage
                )
            }
        }
    }
}