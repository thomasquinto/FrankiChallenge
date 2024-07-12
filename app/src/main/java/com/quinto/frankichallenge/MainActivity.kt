package com.quinto.frankichallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quinto.frankichallenge.ui.WeatherScreen
import com.quinto.frankichallenge.ui.WeatherViewModel
import com.quinto.frankichallenge.ui.theme.FrankiChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FrankiChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val weatherViewModel = viewModel<WeatherViewModel>()

                    WeatherScreen(
                        state = weatherViewModel.state,
                        textChanged = weatherViewModel::fetchWeather
                    )
                }
            }
        }
    }
}

