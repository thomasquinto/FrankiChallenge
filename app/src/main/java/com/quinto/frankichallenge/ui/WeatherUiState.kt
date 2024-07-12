package com.quinto.frankichallenge.ui

import com.quinto.frankichallenge.data.network.Main
import com.quinto.frankichallenge.data.network.Weather

data class WeatherUiState(
    val city: String = "",
    val weatherList: List<Weather> = emptyList(),
    val main: Main = Main()
)