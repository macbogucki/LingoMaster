package com.example.lingomaster.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingomaster.R

@Composable
fun SelectLanguageScreen(
    onCancelButtonClick: () -> Unit = {},
    onSetButtonClick: () -> Unit = {},
    modifier: Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Wybierz język do nauki",
                fontSize = 32.sp)
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.uk_flag),
                contentDescription = "angielski",
                modifier = Modifier.width(300.dp))
            Text(text = "angielski")
        }
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.flag_of_germany_800_480),
                contentDescription = "niemiecki",
                modifier = Modifier.width(300.dp))
            Text(text = "niemiecki")
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
            Button(onClick = onSetButtonClick,
                modifier = Modifier.widthIn(min = 150.dp)){
                Text(text = "Zatwierdź")
            }
        }
    }
}