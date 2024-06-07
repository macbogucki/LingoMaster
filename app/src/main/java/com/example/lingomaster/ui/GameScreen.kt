package com.example.lingomaster.ui

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingomaster.R
import com.example.lingomaster.ui.theme.LingoMasterTheme
import com.google.android.material.bottomsheet.BottomSheetDialog

@Composable
fun GameScreen(
    gameViewModel: GameViewModel,
    onExitButtonClick: () -> Unit,
    modifier: Modifier,
    statsViewModel: StatsViewModel
){
    val gameUiState by gameViewModel.uiState.collectAsState()
    var hasGameEnded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top)
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom) {
            Row(modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = stringResource(id = R.string.punkty) + gameUiState.score.toString(), fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground)
                Text(text = gameUiState.currentWordCount.toString() + " /20", fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground)
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.serce),
                        contentDescription = stringResource(id = R.string.serce),
                        modifier = Modifier.size(28.dp)
                    )
                    Text(
                        gameUiState.userLives.toString(),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
            Text(text = gameUiState.currentCorrectWordPolish, fontSize = 24.sp, modifier = Modifier.padding(top = 64.dp), color = MaterialTheme.colorScheme.onBackground)
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
                    Text(text = stringResource(id = R.string.zla_odpowiedz), fontSize = 20.sp, modifier = Modifier.padding(top = 12.dp), color = MaterialTheme.colorScheme.error)
                    Text(text = stringResource(id = R.string.Poprawna_to)+ gameUiState.currentCorrectWord, color = MaterialTheme.colorScheme.onErrorContainer)
                    Button(
                        onClick = { gameViewModel.updateGameState() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 100.dp, start = 32.dp, top = 16.dp, end = 32.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text(text = stringResource(id = R.string.Kontynuuj), fontSize = 18.sp, modifier = Modifier.padding(4.dp), color = MaterialTheme.colorScheme.onError)
                    }
                }
            }

        } else {
            Column(
                verticalArrangement = Arrangement.Bottom,

                modifier = modifier

            ) {
                Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(id = R.string.Dobra_odpowiedz), fontSize = 18.sp, modifier = Modifier.padding(top = 12.dp), color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Button(
                        onClick = { gameViewModel.updateGameState() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 100.dp, start = 32.dp, top = 16.dp, end = 32.dp)
                    ) {
                        Text(text = stringResource(id = R.string.Kontynuuj), fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                    }
                }
            }
        }
    }
    
    if (gameUiState.isGameOver)
    {


        Column {
            Dialog(
                onDismissRequest = {  }){
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .padding(16.dp),
                    shape = RoundedCornerShape(24.dp)) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)) {
                        Text(text = stringResource(id = R.string.Koniec_gry), fontSize = 24.sp, modifier = Modifier.padding(8.dp))
                        Text(text = stringResource(id = R.string.Zdobyles) + gameUiState.score + stringResource(
                            id = R.string.punktow
                        ), fontSize = 18.sp,  modifier = Modifier.padding(8.dp))
                        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Bottom)
                        {

                            TextButton(onClick = { onExitButtonClick() }) {
                                Text(text = stringResource(id = R.string.Wyjdz), fontSize = 16.sp, modifier = Modifier.padding(8.dp))
                            }
                            TextButton(
                                onClick = { gameViewModel.resetGame()
                                    hasGameEnded = false}
                            ) {
                                Text(text = stringResource(id = R.string.Zagraj_ponownie), fontSize = 16.sp, modifier = Modifier.padding(8.dp))
                            }
                        }
                    }
                }
            }
        }
        LaunchedEffect(gameUiState.isGameOver) {
            if (!hasGameEnded) {
                if (gameUiState.userLives > 0) {
                    statsViewModel.updateWins()
                } else {
                    statsViewModel.updateLosses()
                }
                hasGameEnded = true
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
