package com.example.disenio

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disenio.ui.theme.*
import kotlin.math.sin

class Riego : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisenioTheme {
                AppRiego()
            }
        }
    }
}

@Composable
fun AppRiego() {
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
                InfoCardRiego()
            }
            item {
                InfoCardAguaRiego()

            }
            item {
                SwitchRiego()
            }
            item {
                InfoCardBoton()
            }
            item {

            }
        }
    }
}

@Composable
fun SeccionRiego(texto: String, color: androidx.compose.ui.graphics.Color) {
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
fun InfoCardRiego() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorSecundario
        )
    ) {
        Text(
            text = "Riego",
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
/*
@Composable
fun InfoCardAguaRiego(nivelAgua: Float = 0.87f) {
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
                    text = "87%",
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
*/
@Composable
fun InfoCardAguaRiego(nivelAgua: Float = 0.87f) {
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
        }
    }
}

@Composable
fun InfoCardBoton() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = colorTerciario),
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

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Regar Ahora",
                        style = TextStyle(
                            fontSize = 50.sp,
                            color = colorSecundario,
                            fontFamily = miFuente
                        )
                    )

                }
            }
        }
    }
}

@Composable
fun SwitchRiego() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Modo automático",
            style = TextStyle(
                fontSize = 20.sp,
                color = colorSecundario,
                fontFamily = miFuente
            )
        )
        androidx.compose.material3.Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}




