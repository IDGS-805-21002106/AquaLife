package com.example.disenio

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disenio.ui.theme.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.shape.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import kotlin.math.sin
import kotlin.math.PI

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
            text = "Estado",
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
fun InfoCardAgua(nivelAgua: Float = 0.87f) {
    // 1. Animación de olas
    val infiniteTransition = rememberInfiniteTransition()
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "waveAnimation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)  // Aumentado para mejor visualización
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTerciario
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))  // Recorta el contenido para el efecto de ola
            ) {
                // Contenedor del agua con efecto de olas
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(nivelAgua)
                        .align(Alignment.BottomCenter)
                        .drawWithContent {
                            drawContent()

                            // Efecto de olas usando una función seno
                            for (i in 0..3) {
                                drawPath(
                                    path = Path().apply {
                                        val waveHeight = 15f
                                        val waveLength = size.width / 2

                                        moveTo(0f, size.height)

                                        for (x in 0..size.width.toInt() step 10) {
                                            val y = waveHeight * sin(waveOffset + x.toFloat() / waveLength * 2 * Math.PI.toFloat())
                                            lineTo(x.toFloat(), y)
                                        }

                                        lineTo(size.width, size.height)
                                        close()
                                    },
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            colorSecundario.copy(alpha = 0.9f),
                                            colorSecundario.copy(alpha = 0.7f)
                                        ),
                                        startY = 0f,
                                        endY = 50f
                                    ),
                                    alpha = 0.6f
                                )
                            }
                        }
                        .background(colorSecundario, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                ) {
                    Text(
                        text = "${(nivelAgua * 100).toInt()}%",
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 12.dp),
                        style = TextStyle(
                            fontSize = 48.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontFamily = miFuente,
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.onPrimary,
                                offset = Offset(2f, 2f),
                                blurRadius = 4f
                            )
                        )
                    )
                }
            }

            // Textos inferiores
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Capacidad: 500L",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = miFuente
                    )
                )
                Text(
                    text = "Disponible: ${(nivelAgua * 500).toInt()}L",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface,
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
            // Barra vertical lateral
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
                    .background(
                        colorSecundario,
                        shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                    )
            )

            Icon(
                painter = painterResource(id = R.drawable.nubealerta),
                contentDescription = "Último riego",
                tint = androidx.compose.ui.graphics.Color.Black,
                modifier = Modifier
                    .padding(start = 12.dp, end = 8.dp)
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Último día de riego: ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = androidx.compose.ui.graphics.Color.Black,
                        fontFamily = miFuente
                    )
                )
                Text(
                    text = "Hace 4 días",
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

            Icon(
                painter = painterResource(id = R.drawable.humedad),
                contentDescription = "Humedad del suelo",
                tint = androidx.compose.ui.graphics.Color.Black,
                modifier = Modifier
                    .padding(start = 12.dp, end = 8.dp)
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Humedad del suelo: ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = androidx.compose.ui.graphics.Color.Black,
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

    val screens = listOf(
        MainActivity::class.java,
        Riego::class.java,
        Estadisticas::class.java,
        Api::class.java
    )

    val currentActivity = (context as? ComponentActivity)?.javaClass

    val items = listOf(
        Triple(R.drawable.water_drop_24dp_ffffff_fill0_wght400_grad0_opsz24, "Estado", MainActivity::class.java),
        Triple(R.drawable.icono2v3, "Riego", Riego::class.java),
        Triple(R.drawable.icono3v3, "Estadisticas", Estadisticas::class.java),
        Triple(R.drawable.api, "Api", Api::class.java)
    )

    NavigationBar {
        items.forEach { item ->
            val isSelected = currentActivity == item.third

            NavigationBarItem(
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.first),
                            contentDescription = item.second,
                            tint = if (isSelected) Color(0xFF00C7BE) else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(32.dp)
                        )
                        Text(
                            text = item.second,
                            fontSize = 12.sp,
                            color = if (isSelected) Color(0xFF00C7BE) else MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        context.startActivity(Intent(context, item.third))

                        if (context is ComponentActivity) {
                            context.overridePendingTransition(0, 0)
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

/*
@Composable
fun BottomNavBar() {
    val context = LocalContext.current

    val items = listOf(
        Pair(R.drawable.water_drop_24dp_ffffff_fill0_wght400_grad0_opsz24, "Estado"),
        Pair(R.drawable.icono2v3, "Riego"),
        Pair(R.drawable.icono3v3, "Estadisticas")
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
*/

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    DisenioTheme {
        App()
    }
}
