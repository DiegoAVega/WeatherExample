package com.example.weatherexample

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getWeather(
        @Query("appid") appId: String,
        @Query("q") city: String,
        @Query("units") units: String,
    ): Response<WeatherResponse>

}

object RetrofitServiceFactory {

    fun makeRetrofitService(): WeatherService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

}

class WeatherResponse (
    val main: Main,
    val weather: List<Weather>
)

class Weather(
    val description: String,
    val icon: String
)

class Main(
    val temp: Double
)