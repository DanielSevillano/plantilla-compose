package com.daniel.plantillacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.daniel.plantillacompose.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val destinoActual = navBackStackEntry?.destination

                            destinos.forEach { destino ->
                                val destinoSeleccionado =
                                    destinoActual?.hierarchy?.any { it.route == destino.ruta } == true

                                NavigationBarItem(
                                    selected = destinoSeleccionado,
                                    onClick = {
                                        navController.navigate(destino.ruta) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (destinoSeleccionado) destino.iconoSeleccionado else destino.icono,
                                            contentDescription = destino.ruta
                                        )
                                    },
                                    label = {
                                        Text(text = destino.ruta)
                                    }
                                )
                            }

                        }
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = destinos.first().ruta
                            ) {
                                composable("Destino 1") { Text(text = "Destino 1") }
                                composable("Destino 2") { Text(text = "Destino 2") }
                                composable("Destino 3") { Text(text = "Destino 3") }
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class Destino(val ruta: String, val icono: ImageVector, val iconoSeleccionado: ImageVector) {
    object Destino1 : Destino("Destino 1", Icons.Outlined.Home, Icons.Filled.Home)
    object Destino2 : Destino("Destino 2", Icons.Outlined.CheckCircle, Icons.Filled.CheckCircle)
    object Destino3 : Destino("Destino 3", Icons.Outlined.AccountCircle, Icons.Filled.AccountCircle)
}

val destinos = listOf(
    Destino.Destino1,
    Destino.Destino2,
    Destino.Destino3
)