package com.daniel.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.daniel.compose.ui.theme.AppTheme
import soup.compose.material.motion.animation.materialFadeThroughIn
import soup.compose.material.motion.animation.materialFadeThroughOut

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val destinoActual = navBackStackEntry?.destination

                            Destino.entries.forEach { destino ->
                                val destinoSeleccionado =
                                    destinoActual?.hierarchy?.any { it.route == destino.ruta } == true

                                NavigationBarItem(
                                    selected = destinoSeleccionado,
                                    onClick = {
                                        if (!destinoSeleccionado) {
                                            navController.navigate(destino.ruta) {
                                                popUpTo(navController.graph.findStartDestination().id)
                                                launchSingleTop = true
                                            }
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (destinoSeleccionado) destino.iconoSeleccionado else destino.icono,
                                            contentDescription = stringResource(id = destino.nombre)
                                        )
                                    },
                                    label = {
                                        Text(text = stringResource(id = destino.nombre))
                                    }
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Destino.entries.first().ruta,
                            enterTransition = { materialFadeThroughIn() },
                            exitTransition = { materialFadeThroughOut() }
                        ) {
                            composable(route = Destino.Destino1.ruta) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = stringResource(id = Destino.Destino1.nombre))
                                }
                            }

                            composable(route = Destino.Destino2.ruta) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = stringResource(id = Destino.Destino2.nombre))
                                }
                            }

                            composable(route = Destino.Destino3.ruta) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = stringResource(id = Destino.Destino3.nombre))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}