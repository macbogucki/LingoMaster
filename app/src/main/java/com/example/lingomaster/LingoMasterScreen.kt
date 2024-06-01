package com.example.lingomaster

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lingomaster.ui.GameScreen
import com.example.lingomaster.ui.StartScreen
import com.example.lingomaster.ui.theme.LingoMasterTheme

enum class LingoMasterScreen()
{
    Start, Game
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LingoMasterAppTopBar(modifier: Modifier = Modifier){
    TopAppBar(
        title = { "LingoMaster" },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier)
}

@Composable
fun LingoMasterApp(navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {
            LingoMasterAppTopBar()
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LingoMasterScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = LingoMasterScreen.Start.name){
                StartScreen(modifier = Modifier.fillMaxSize())
            }
            composable(route = LingoMasterScreen.Game.name){
                GameScreen(modifier = Modifier)
            }

        }
        
    }
}



//@Preview
//@Composable
//fun StartScreenPreview() {
//    LingoMasterTheme {
//        StartScreen(
//            modifier = Modifier
//                .fillMaxSize()
//
//        )
//    }
//}

//@Preview
//@Composable
//fun GameScreenPreview() {
//    LingoMasterTheme {
//        GameScreen(
//            modifier = Modifier
//                .fillMaxSize()
//
//        )
//    }
//}
