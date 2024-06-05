package com.example.lingomaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.lingomaster.ui.theme.LingoMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LingoMasterTheme {
                LingoMasterApp()
            }
        }
    }
    override fun onStop() {
        super.onStop()

    }
}
//ikona aplikacji
//<a href="https://www.flaticon.com/free-icons/academy" title="academy icons">Academy icons created by afif fudin - Flaticon</a>
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LingoMasterTheme {
        LingoMasterApp()
    }
}

