package com.example.lingomaster.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingomaster.ui.theme.LingoMasterTheme
import com.google.android.material.bottomsheet.BottomSheetDialog

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel(),
    onExitButtonClick: () -> Unit = {},
    modifier: Modifier
){
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top)
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom) {
            Row(modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = "punkty: "+ gameUiState.score.toString(), fontSize = 18.sp)
                Text(text = gameUiState.currentWordCount.toString() + " /20", fontSize = 18.sp)
                Text(text = "życia: " + gameUiState.userLives.toString(), fontSize = 18.sp)
            }
            Text(text = gameUiState.currentCorrectWordPolish, fontSize = 24.sp, modifier = Modifier.padding(top = 64.dp))
        }
        Spacer(modifier = Modifier.padding(48.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            gameUiState.currentPossibleAnswers.forEach { possibleAnswer ->
                possibleAnswerButton(possibleAnswer = possibleAnswer) {gameViewModel.CheckUserAnswer(possibleAnswer)}}
        }
    }
    if (gameUiState.isShowDialogAlert) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
                .clickable(
                    onClick = {},
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )
        if (gameUiState.isGuessedWordWrong) {
            Column(
                verticalArrangement = Arrangement.Bottom,

                modifier = modifier
                    
            ) {
                Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.errorContainer),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Zła odpowiedź", fontSize = 18.sp, modifier = Modifier.padding(top = 12.dp), color = MaterialTheme.colorScheme.onErrorContainer)
                    Text(text = "Poprawna to: "+ gameUiState.currentCorrectWord)
                    Button(
                        onClick = { gameViewModel.updateGameState() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 100.dp, start = 32.dp, top = 16.dp, end = 32.dp)
                    ) {
                        Text(text = "Kontynuuj", fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                    }
                }
            }

        } else {
            Column(
                verticalArrangement = Arrangement.Bottom,

                modifier = modifier

            ) {
                Column(modifier = Modifier.background(color = Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Dobra odpowiedź", fontSize = 18.sp, modifier = Modifier.padding(top = 12.dp))
                    Button(
                        onClick = { gameViewModel.updateGameState() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 100.dp, start = 32.dp, top = 16.dp, end = 32.dp)
                    ) {
                        Text(text = "Kontynuuj", fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                    }
                }
            }
        }
    }
    
    if (gameUiState.isGameOver)
    {
        Column {
            Dialog(
                onDismissRequest = { /*TODO*/ }){
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .padding(16.dp),
                    shape = RoundedCornerShape(24.dp)) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)) {
                        Text(text = "Koniec gry", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
                        Text(text = "Zdobyłeś " + gameUiState.score + " punktów", fontSize = 18.sp,  modifier = Modifier.padding(8.dp))
                        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Bottom)
                        {

                            TextButton(onClick = onExitButtonClick) {
                                Text(text = "Wyjdź", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
                            }
                            TextButton(
                                onClick = { gameViewModel.resetGame() }
                            ) {
                                Text(text = "Zagraj ponownie", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun possibleAnswerButton(
    possibleAnswer: String,
    onAnswerButtonClick: () -> Unit
){
    Button(onClick = onAnswerButtonClick,
        modifier = Modifier.widthIn(min = 200.dp)
    ) {
        Text(text = possibleAnswer, fontSize = 18.sp, modifier = Modifier.padding(4.dp))
    }
}
@Preview
@Composable
fun GameScreenPreview() {
    LingoMasterTheme {
        GameScreen(
            modifier = Modifier
                .fillMaxSize()

        )
    }
}