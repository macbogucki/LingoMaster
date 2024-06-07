package com.example.lingomaster

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lingomaster.ui.GameScreen
import com.example.lingomaster.ui.GameViewModel
import com.example.lingomaster.ui.SelectLanguageScreen
import com.example.lingomaster.ui.StartScreen
import com.example.lingomaster.ui.StatsScreen
import com.example.lingomaster.ui.StatsViewModel
import com.example.lingomaster.ui.theme.LingoMasterTheme

enum class LingoMasterScreen()
{
    Start, Game, Language, Stats
}


@Composable
fun LingoMasterApp(
    navController: NavHostController = rememberNavController(),
    gameViewModel: GameViewModel = viewModel(),
    statsViewModel: StatsViewModel) {

    val statsList by statsViewModel.allStats.observeAsState(initial = emptyList())

    if (statsList.isNotEmpty()) {
        gameViewModel.setNewLanguage(statsList.first().language)
    }

    NavHost(
        navController = navController,
        startDestination = LingoMasterScreen.Start.name,

        ) {
        composable(route = LingoMasterScreen.Start.name) {
            StartScreen(
                onGameButtonClick = { navController.navigate(LingoMasterScreen.Game.name) },
                onLangSelectButtonClick = { navController.navigate(LingoMasterScreen.Language.name) },
                onStatsButtonClick = { navController.navigate(LingoMasterScreen.Stats.name) },
                modifier = Modifier.fillMaxSize(),
                gameViewModel = gameViewModel
            )
        }
        composable(route = LingoMasterScreen.Game.name) {
            GameScreen(
                modifier = Modifier.fillMaxSize(),
                onExitButtonClick = {navController.navigate(LingoMasterScreen.Start.name)},
                gameViewModel = gameViewModel,
                statsViewModel = statsViewModel
            )
        }
        composable(route = LingoMasterScreen.Language.name) {
            SelectLanguageScreen(
                gameViewModel = gameViewModel,
                onCancelButtonClick = { navController.navigate(LingoMasterScreen.Start.name) },
                onSetButtonClick = { navController.navigate(LingoMasterScreen.Start.name) },
                modifier = Modifier.fillMaxSize(),
                statsViewModel = statsViewModel,
                statsList = statsList
            )
        }
        composable(route = LingoMasterScreen.Stats.name) {
            StatsScreen(
                gameViewModel = gameViewModel,
                onCancelButtonClick = { navController.navigate(LingoMasterScreen.Start.name) },
                modifier = Modifier.fillMaxSize(),
                statsViewModel = statsViewModel,
                statsList = statsList
            )
        }
    }
}

