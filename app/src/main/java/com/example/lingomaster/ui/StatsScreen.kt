package com.example.lingomaster.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingomaster.R
import com.example.lingomaster.data.StatsData

@Composable
fun StatsScreen(
    gameViewModel: GameViewModel,
    onCancelButtonClick: () -> Unit = {},
    modifier: Modifier,
    statsViewModel: StatsViewModel,
    statsList: List<StatsData>
){
    val gameUiState by gameViewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    //val stats = statsDao
    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.Statystyki),
                fontSize = 32.sp, color = MaterialTheme.colorScheme.onBackground)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            if (statsList.isNotEmpty()) {
                val stats = statsList.first()
                Text(text = stringResource(id = R.string.Liczba_gier)+ stats.games, color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp)
                Text(text = stringResource(id = R.string.Wygrane) + stats.wins, color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp)
                Text(text = stringResource(id = R.string.Przegrane)+ stats.failures, color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp)
            } else {
                Text(text = stringResource(id = R.string.brak_statystyk))
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onCancelButtonClick,
                modifier = Modifier.widthIn(min = 150.dp)) {
                Text(text = stringResource(id = R.string.cofnij))
            }
            Button(onClick = { showDialog = true },
                modifier = Modifier.widthIn(min = 150.dp)) {
                Text(text = stringResource(id = R.string.reset))
            }
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = stringResource(id = R.string.Reset_Statystyk)) },
                text = { Text(text = stringResource(id = R.string.czy_chcesz_reset)) },
                confirmButton = {
                    Button(onClick = {
                        statsViewModel.resetStats()
                        showDialog = false
                    },colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                        Text(stringResource(id = R.string.reset), color = MaterialTheme.colorScheme.onError)
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }, modifier = Modifier.padding(end = 24.dp)) {
                        Text(stringResource(id = R.string.cofnij))
                    }
                }
            )
        }
    }
}
