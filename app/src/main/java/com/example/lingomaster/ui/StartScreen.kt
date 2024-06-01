package com.example.lingomaster.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(modifier: Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.widthIn(min = 200.dp)
            ) {
                Text(text = "Start")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.widthIn(min = 200.dp)
            ) {
                Text(text = "Wybierz język")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.widthIn(min = 200.dp)) {
                Text(text = "Statystyki")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.widthIn(min = 200.dp)) {
                Text(text = "Czwarty")
            }
        }

    }
}
