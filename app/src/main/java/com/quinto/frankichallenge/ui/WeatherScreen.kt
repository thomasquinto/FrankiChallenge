package com.quinto.frankichallenge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WeatherScreen(
    state: WeatherUiState,
    textChanged: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val searchQuery = remember { mutableStateOf(state.city) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally)
    {

        Text(text = "Enter City Name")
        TextField(
            value = searchQuery.value,
            maxLines = 1,
            onValueChange = { newValue ->
                coroutineScope.launch {
                    delay(500) // delay for 0.5 seconds
                    if (newValue == searchQuery.value) {
                        textChanged(newValue)
                    }
                }
                searchQuery.value = newValue
            }
        )

        if (state.main.temp != 0.0) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    text = "Temperature:"
                )

                Text(text = "Current: ${state.main.temp}")
                Text(text = "Feels Like: ${state.main.feels_like}")
                Text(text = "Min Temp: ${state.main.temp_min}")
                Text(text = "Max Temp: ${state.main.temp_max}")
            }
        }

        if(state.weatherList.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(16.dp),
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                text = "Weather conditions:"
            )
        }
        LazyColumn() {
            items(state.weatherList.size) { weather ->
                Text(text = state.weatherList[weather].description)
            }
        }

    }
}