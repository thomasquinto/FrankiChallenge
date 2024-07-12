package com.quinto.frankichallenge.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quinto.frankichallenge.data.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    var state by mutableStateOf(WeatherUiState())

    private val repository = WeatherRepository()
    private var fetchWeatherJob: Job? = null;

    fun fetchWeather(city: String) {
        fetchWeatherJob?.cancel()
        fetchWeatherJob = viewModelScope.launch(Dispatchers.IO) {
            repository.fetchWeatherItems(city).collect { result ->
                state = state.copy(city = city, weatherList = result.weather, main = result.main)
            }
        }
    }
}