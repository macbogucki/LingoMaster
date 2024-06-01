package com.example.lingomaster.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen(modifier: Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly) {
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                //to do 3 ikony serca
            }
            Text(text = "slowo")
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.widthIn(min = 200.dp)
            ) {
                Text(text = "Pierwszy")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.widthIn(min = 200.dp)
            ) {
                Text(text = "Drugi")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.widthIn(min = 200.dp)) {
                Text(text = "Trzeci")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.widthIn(min = 200.dp)) {
                Text(text = "Czwarty")
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Dobrze")
        }
    }
}
