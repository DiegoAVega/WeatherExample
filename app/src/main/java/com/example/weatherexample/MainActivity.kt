package com.example.weatherexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var textViewTemperature: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewTemperature = findViewById(R.id.textViewTemperature)
        val weatherService = RetrofitServiceFactory.makeRetrofitService()
        lifecycleScope.launch {
            val response = weatherService.getWeather("bd6abe5bcdddf0126b47e2f85aa84476","Lima","metric")
            textViewTemperature.text = response.main.temp.toString()
        }
    }

}