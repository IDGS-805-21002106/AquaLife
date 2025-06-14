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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disenio.ui.theme.*

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
                SeccionRiego("AquaLife", colorPrimario)
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
fun InfoCardAguaRiego(nivelAgua: Float = 0.9f) {
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




