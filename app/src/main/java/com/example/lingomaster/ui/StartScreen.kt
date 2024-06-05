package com.example.lingomaster.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingomaster.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LingoMasterAppTopBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.mipmap.logo_static), contentDescription = "logo",
                    modifier.size(80.dp)
                        .padding(8.dp))
                Text(text = "LingoMaster", fontSize = 32.sp, color = MaterialTheme.colorScheme.onPrimary)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier)
}


@Composable
fun StartScreen(
    onGameButtonClick: () -> Unit,
    onLangSelectButtonClick: () -> Unit = {},
    onStatsButtonClick: () -> Unit = {},
    modifier: Modifier,
    gameViewModel: GameViewModel) {
    Scaffold(
        topBar = {
            LingoMasterAppTopBar()
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Button(
                    onClick = {
                        gameViewModel.resetGame()
                        onGameButtonClick()},
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Start", fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                }
                Button(
                    onClick = onLangSelectButtonClick,
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Wybierz język", fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                }
                Button(
                    onClick = onStatsButtonClick,
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Statystyki", fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.widthIn(min = 200.dp)
                ) {
                    Text(text = "Czwarty", fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                }
            }

        }
    }
}
