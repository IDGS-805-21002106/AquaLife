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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import coil.compose.AsyncImage

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
            .height(150.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = colorTerciario),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
                    .background(
                        colorSecundario,
                        shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                    )
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (weather != null) {
                    val current = weather!!.current

                    Text(
                        text = "Clima actual en León",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontFamily = miFuente
                        )
                    )

                    Row {
                        Text(text = "Temperatura: ", fontSize = 14.sp, fontFamily = miFuente)
                        Text(
                            text = "${current.tempC}°C",
                            fontSize = 14.sp,
                            color = colorSecundario,
                            fontFamily = miFuente
                        )
                    }

                    Row {
                        Text(text = "Condición: ", fontSize = 14.sp, fontFamily = miFuente)
                        Text(
                            text = current.condition.text,
                            fontSize = 14.sp,
                            color = colorSecundario,
                            fontFamily = miFuente
                        )
                    }

                    Row {
                        Text(text = "Humedad: ", fontSize = 14.sp, fontFamily = miFuente)
                        Text(
                            text = "${current.humidity}%",
                            fontSize = 14.sp,
                            color = colorSecundario,
                            fontFamily = miFuente
                        )
                    }

                    Row {
                        Text(text = "Viento: ", fontSize = 14.sp, fontFamily = miFuente)
                        Text(
                            text = "${current.windKph} km/h",
                            fontSize = 14.sp,
                            color = colorSecundario,
                            fontFamily = miFuente
                        )
                    }
                } else if (error != null) {
                    Text(
                        text = error ?: "Error desconocido",
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontFamily = miFuente
                    )
                } else {
                    Text(
                        text = "Cargando...",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        fontFamily = miFuente
                    )
                }
            }

            if (weather != null) {
                val iconUrl = "https:" + weather!!.current.condition.icon

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = iconUrl,
                        contentDescription = weather!!.current.condition.text,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }
    }
}




