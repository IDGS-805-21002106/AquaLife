package com.example.disenio

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.disenio.WeatherResponse
import com.example.disenio.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCurrentWeather(
                    apiKey = "846d69230b684d6385872512250307",
                    city = city
                )
                _weather.value = response
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al obtener datos: ${e.message}"
                e.printStackTrace()
            }
        }
    }
}