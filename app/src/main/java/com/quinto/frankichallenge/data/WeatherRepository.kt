package com.quinto.frankichallenge.data

import com.quinto.frankichallenge.data.network.WeatherApi
import com.quinto.frankichallenge.data.network.WeatherResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class WeatherRepository {

    suspend fun fetchWeatherItems(
        city: String
    ): Flow<WeatherResponse> {
        return flow {
            try {
                val weatherResponse =
                    getWeatherApi().getWeather(appId = WeatherApi.APP_ID, city = city)
                emit(weatherResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun getWeatherApi(): WeatherApi {
        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create()
    }
}
