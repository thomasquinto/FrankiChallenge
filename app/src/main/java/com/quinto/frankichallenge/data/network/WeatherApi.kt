package com.quinto.frankichallenge.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    //Example URL: https://api.openweathermap.org/data/2.5/weather?q=Los%20Angeles&appid=5deca2f99f97d972562a33188d696b4a

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val APP_ID = "5deca2f99f97d972562a33188d696b4a"
    }

    @GET("weather")
    suspend fun getWeather(
        @Query("appId") appId: String,
        @Query("q") city: String
    ) : WeatherResponse
}

data class WeatherResponse (
    val weather: List<Weather>,
    val main: Main,
)

data class Weather (
    val main: String,
    val description: String,
)

data class Main(
    val temp: Double = 0.0,
    val feels_like: Double = 0.0,
    val temp_min: Double = 0.0,
    val temp_max: Double = 0.0
)
