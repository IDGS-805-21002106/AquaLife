package com.example.disenio

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disenio.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisenioTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    Scaffold(
        bottomBar = { BottomNavBar() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Seccion("AquaLife", colorPrimario)
            }
            item {
                InfoCard()
            }
            item {
                InfoCardAgua()
            }
            item {
                InfoCardUno()
            }
            item {
                InfoCardDos()
            }
        }
    }
}

@Composable
fun Seccion(texto: String, color: androidx.compose.ui.graphics.Color) {
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
fun InfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorSecundario
        )
    ) {
        Text(
            text = "Sistema de riego automatizado",
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
fun InfoCardAgua(nivelAgua: Float = 0.9f) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTerciario
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp)) // fondo del estanque
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(nivelAgua)
                    .align(Alignment.BottomCenter)
                    .background(colorSecundario, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ){

            Text(
                text = "95%",
                modifier = Modifier.align(Alignment.TopCenter),
                style = TextStyle(
                    fontSize = 48.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontFamily = miFuente
                )
            )
                }

        }
    }
}

@Composable
fun InfoCardUno() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTerciario
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
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

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ultimo dia de riego",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = miFuente
                    )
                )
                Text(
                    text = "Hace 4 dias",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = colorSecundario,
                        fontFamily = miFuente
                    )
                )
            }
        }
    }
}


@Composable
fun InfoCardDos() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTerciario
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
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

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Humedad del suelo",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = miFuente
                    )
                )
                Text(
                    text = "68%",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = colorSecundario,
                        fontFamily = miFuente
                    )
                )
            }
        }
    }
}



@Composable
fun BottomNavBar() {
    val context = LocalContext.current

    val items = listOf(
        Pair(R.drawable.icon1, "Estado"),
        Pair(R.drawable.icon2, "Riego"),
        Pair(R.drawable.icon3, "Estadisticas")
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.first),
                            contentDescription = item.second,
                            modifier = Modifier.size(32.dp)
                        )
                        Text(
                            text = item.second,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                selected = false,
                onClick = {
                    when (item.second) {
                        "Riego" -> context.startActivity(Intent(context, Riego::class.java))
                        "Estadisticas" -> context.startActivity(Intent(context, Estadisticas::class.java))
                        "Estado" -> context.startActivity(Intent(context, MainActivity::class.java))
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    DisenioTheme {
        App()
    }
}
