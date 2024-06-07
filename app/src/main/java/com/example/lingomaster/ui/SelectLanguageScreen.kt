package com.example.lingomaster.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingomaster.R
import com.example.lingomaster.data.StatsData
import com.example.lingomaster.ui.theme.backgroundLight

@Composable
fun SelectLanguageScreen(
    gameViewModel: GameViewModel,
    statsViewModel: StatsViewModel,
    onCancelButtonClick: () -> Unit = {},
    onSetButtonClick: () -> Unit,
    modifier: Modifier,
    statsList: List<StatsData>

){
    val gameUiState by gameViewModel.uiState.collectAsState()

    var selectedLanguage = remember { mutableStateOf(statsList.first().language) }


    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.jezyk_do_nauki),
                fontSize = 32.sp, color = MaterialTheme.colorScheme.onBackground)
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.uk_flag),
                contentDescription = stringResource(id = R.string.angielski),
                modifier = Modifier
                    .width(300.dp)
                    .clickable {
                        selectedLanguage.value = "angielski"
                    })
            Text(text = stringResource(id = R.string.angielski), color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp)
        }
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.flag_of_germany_800_480),
                contentDescription = stringResource(id = R.string.niemiecki),
                modifier = Modifier
                    .width(300.dp)
                    .clickable {
                        selectedLanguage.value = "niemiecki"
                    })
            Text(text = stringResource(id = R.string.niemiecki), color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp)
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.obecnie_wybrany) + selectedLanguage.value, fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground)
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
            Button(
                onClick = {
                    gameViewModel.setNewLanguage(selectedLanguage.value)
                    statsViewModel.changeLanguage(selectedLanguage.value)
                    onSetButtonClick() },
                modifier = Modifier.widthIn(min = 150.dp)){
                Text(text = stringResource(id = R.string.Zatwierdz))
            }
        }
    }
}