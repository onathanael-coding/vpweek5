package com.oliver.vpweek5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.oliver.vpweek5.Soal1.ui.screen.Soal1ExpenseScreen
import com.oliver.vpweek5.Soal1.ui.theme.Vpweek5Theme
import com.oliver.vpweek5.Soal2.ui.screen.Soal2PageStateScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vpweek5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        Soal2PageStateScreen()
                    }
                }
            }
        }
    }
}
