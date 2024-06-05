package com.example.lingomaster.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingomaster.R

@Composable
fun StatsScreen(
    gameViewModel: GameViewModel,
    onCancelButtonClick: () -> Unit = {},
    modifier: Modifier
){
    val gameUiState by gameViewModel.uiState.collectAsState()
    //val stats = statsDao
    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Statystyki",
                fontSize = 32.sp, color = MaterialTheme.colorScheme.onBackground)
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 32.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(text = "Liczba gier: ", color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp)
            Text(text = "Wygrane: ", color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp)
            Text(text = "Przegrane: ", color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onCancelButtonClick,
                modifier = Modifier.widthIn(min = 150.dp)) {
                Text(text = "Cofnij")
            }
        }
    }
}