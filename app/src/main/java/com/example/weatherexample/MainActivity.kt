package com.example.weatherexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var textViewTemperature: TextView
    private lateinit var textViewLocationName: TextView
    private lateinit var editTextLocation: EditText
    private lateinit var buttonSearch: Button
    private val weatherService = RetrofitServiceFactory.makeRetrofitService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewTemperature = findViewById(R.id.textViewTemperature)
        editTextLocation = findViewById(R.id.editTextTextLocation)
        buttonSearch = findViewById(R.id.buttonSearch)
        textViewLocationName = findViewById(R.id.textViewLocationName)
        buttonSearch.setOnClickListener {
            val city = editTextLocation.text.toString()
            textViewLocationName.text = city
            getTemperature(city)
        }
        getTemperature("Lima")
    }

    fun getTemperature(city: String) {
        lifecycleScope.launch {
            val response = weatherService.getWeather(
                "bd6abe5bcdddf0126b47e2f85aa84476",
                city,
                "metric"
            )
            textViewTemperature.text = "${response.main.temp.toString()}°C"
        }
    }

}