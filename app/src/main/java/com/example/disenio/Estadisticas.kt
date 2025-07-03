package com.example.disenio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disenio.ui.theme.DisenioTheme
import com.example.disenio.ui.theme.colorPrimario
import com.example.disenio.ui.theme.colorSecundario
import com.example.disenio.ui.theme.colorTerciario
import com.example.disenio.ui.theme.miFuente

class Estadisticas: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisenioTheme {
                AppEstadis()
            }
        }
    }

}

@Composable
fun AppEstadis() {
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
                InfoCardEstadis()
            }
            item {
                InfoCardEstadis2()
            }
            item {
                InfoCardEstadis3()
            }
            item {
                InfoCardGraficaBarras()
            }
            item {
                InfoCardEstadis4()
            }

        }
    }
}


@Composable
fun SeccionEstadis(texto: String, color: androidx.compose.ui.graphics.Color) {
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
fun InfoCardEstadis() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorSecundario
        )
    ) {
        Text(
            text = "Estadisticas",
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
fun InfoCardEstadis2() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTerciario
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Últimos días de lluvia:",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontFamily = miFuente
                )
            )

            Text(
                text = "- 18/05/2024\n- 15/05/2024\n- 10/05/2024",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontFamily = miFuente
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

//Grafica simulada oseaso no es la chida


@Composable
fun InfoCardEstadis3() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTerciario
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Últimos días de riego:",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontFamily = miFuente
                )
            )

            Text(
                text = "- 18/05/2024\n- 15/05/2024\n- 10/05/2024",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontFamily = miFuente
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


@Composable
fun InfoCardGraficaBarras() {
    val datos = listOf(
        "Enero" to 30f,
        "Febrero" to 20f,
        "Marzo" to 10f,
        "Abril" to 25f
    )
    val maxValor = datos.maxOfOrNull { it.second } ?: 1f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = colorTerciario)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Grafica de lluvia",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontFamily = miFuente
                )
            )

            datos.forEach { (fecha, valor) ->
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(
                        text = fecha,
                        fontSize = 14.sp,
                        color = androidx.compose.ui.graphics.Color.Black,
                        fontFamily = miFuente
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .background(androidx.compose.ui.graphics.Color.LightGray)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(valor / maxValor)
                                .background(colorSecundario)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun InfoCardEstadis4() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTerciario
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Consumo de agua:",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontFamily = miFuente
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Hoy",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = androidx.compose.ui.graphics.Color.Black,
                        fontFamily = miFuente
                    )
                )
                Text(
                    text = "Esta semana",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = androidx.compose.ui.graphics.Color.Black,
                        fontFamily = miFuente
                    )
                )
                Text(
                    text = "Este mes",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = androidx.compose.ui.graphics.Color.Black,
                        fontFamily = miFuente
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "30L",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = colorSecundario,
                        fontFamily = miFuente
                    )
                )
                Text(
                    text = "40L",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = colorSecundario,
                        fontFamily = miFuente
                    )
                )
                Text(
                    text = "239L",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = colorSecundario,
                        fontFamily = miFuente
                    )
                )
            }
        }
    }
}


