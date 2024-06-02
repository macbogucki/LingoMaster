package com.example.lingomaster.ui

import com.example.lingomaster.data.allWords

class GameViewModel {

    private var usedWords: MutableSet<String> = mutableSetOf()
    private var possibleAnswers: MutableSet<String> = mutableSetOf()
    fun DrawCorrectAnswer(){
        val correctAnswer = allWords.random()
        if (usedWords.contains(correctAnswer))
        {
            return DrawCorrectAnswer()
        }
        else{
            usedWords.add(correctAnswer)
        }
    }

    fun DrawSingleAnswer(){
        val answer = allWords.random()
        if (possibleAnswers.contains(answer))
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

    }

    fun CheckUserAnswer(){

    }
}