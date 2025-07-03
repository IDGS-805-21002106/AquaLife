package com.example.disenio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

import com.example.disenio.WeatherViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disenio.ui.theme.DisenioTheme
import com.example.disenio.ui.theme.colorPrimario
import com.example.disenio.ui.theme.colorSecundario
import com.example.disenio.ui.theme.colorTerciario
import com.example.disenio.ui.theme.miFuente

class Api: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisenioTheme {
                AppApi()
            }
        }
    }

}

@Composable
fun AppApi() {
    Scaffold(
        bottomBar = { BottomNavBar() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorPrimario)
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "AquaLife",
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontFamily = miFuente
                        )
                    )
                    Text(
                        text = "Monitoreo de sistema de riego",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                            fontFamily = miFuente
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            item {
                InfoCardApi1()
            }

            item {
                InfoCardApi2()
            }
        }
    }
}

@Composable
fun SeccionApi(texto: String, color: androidx.compose.ui.graphics.Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = texto,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.surface,
                fontFamily = miFuente
            )
        )
    }
}

@Composable
fun InfoCardApi1() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorSecundario
        )
    ) {
        Text(
            text = "Clima",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = miFuente
            )
        )
    }
}

@Composable
fun InfoCardApi2(viewModel: WeatherViewModel = viewModel()) {
    val weather by viewModel.weather.collectAsState()
    val error by viewModel.error.collectAsState()

    var isCalled by remember { mutableStateOf(false) }

    if (!isCalled) {
        LaunchedEffect(Unit) {
            viewModel.fetchWeather("León")
            isCalled = true
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorSecundario)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Clima actual en León",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontFamily = miFuente
                )
            )

            when {
                error != null -> {
                    Text(
                        text = error ?: "Error desconocido"
                    )
                }
                weather != null -> {
                    Text(text = "Temperatura: ${weather?.current?.tempC}°C")
                    Text(text = "Condición: ${weather?.current?.condition?.text}")
                    Text(text = "Humedad: ${weather?.current?.humidity}%")
                    Text(text = "Viento: ${weather?.current?.windKph} km/h")
                }
                else -> {
                    Text(text = "Cargando...")
                }
            }
        }
    }
}

