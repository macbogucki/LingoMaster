package com.example.lingomaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.lingomaster.data.StatsApplication
import com.example.lingomaster.data.StatsRepository
import com.example.lingomaster.data.StatsRoomDatabase
import com.example.lingomaster.ui.StatsViewModel
import com.example.lingomaster.ui.StatsViewModelFactory
import com.example.lingomaster.ui.theme.LingoMasterTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainActivity : ComponentActivity() {
//    private val statsViewModel: StatsViewModel by viewModels {
//        StatsViewModelFactory((application as StatsApplication).repository)
//    }
    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { StatsRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { StatsRepository(database.statsDao()) }
    private val statsViewModel: StatsViewModel by viewModels { StatsViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LingoMasterTheme {
                LingoMasterApp(statsViewModel = statsViewModel)
            }
        }
    }

}
//ikona aplikacji
//<a href="https://www.flaticon.com/free-icons/academy" title="academy icons">Academy icons created by afif fudin - Flaticon</a>
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LingoMasterTheme {
//        LingoMasterApp()
//    }
//}

