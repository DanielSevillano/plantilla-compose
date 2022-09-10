package com.daniel.plantillacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.daniel.plantillacompose.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val destinoActual = remember(navBackStackEntry) { navBackStackEntry?.destination }
                val dimensionesVentana = calculateWindowSizeClass(this)

                Scaffold(
                    bottomBar = {
                        if (dimensionesVentana.widthSizeClass == WindowWidthSizeClass.Compact) {
                            NavigationBar {
                                Destino.values().forEach { destino ->
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
                                                contentDescription = stringResource(id = destino.nombreId)
                                            )
                                        },
                                        label = {
                                            Text(text = stringResource(id = destino.nombreId))
                                        }
                                    )
                                }
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
                        when (dimensionesVentana.widthSizeClass) {
                            WindowWidthSizeClass.Expanded -> PantallaExpandida(
                                navController = navController,
                                destinoActual = destinoActual
                            )
                            WindowWidthSizeClass.Medium -> PantallaMediana(
                                navController = navController,
                                destinoActual = destinoActual
                            )
                            else -> Navegacion(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Navegacion(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destino.values().first().ruta
    ) {
        Destino.values().forEach { destino ->
            composable(destino.ruta) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = destino.nombreId))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaExpandida(navController: NavHostController, destinoActual: NavDestination?) {
    PermanentNavigationDrawer(drawerContent = {
        PermanentDrawerSheet(modifier = Modifier.padding(horizontal = 12.dp)) {
            Spacer(modifier = Modifier.height(8.dp))

            Destino.values().forEach { destino ->
                val seleccionado =
                    destinoActual?.hierarchy?.any { it.route == destino.ruta } == true
                NavigationDrawerItem(
                    selected = seleccionado,
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
                            imageVector = if (seleccionado) destino.iconoSeleccionado else destino.icono,
                            contentDescription = stringResource(id = destino.nombreId)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = destino.nombreId)
                        )
                    }
                )
            }
        }
    }) {
        Navegacion(navController = navController)
    }
}

@Composable
fun PantallaMediana(navController: NavHostController, destinoActual: NavDestination?) {
    Row {
        NavigationRail {
            Spacer(Modifier.weight(1f))

            Destino.values().forEach { destino ->
                val seleccionado =
                    destinoActual?.hierarchy?.any { it.route == destino.ruta } == true
                NavigationRailItem(
                    selected = seleccionado,
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
                            imageVector = if (seleccionado) destino.iconoSeleccionado else destino.icono,
                            contentDescription = stringResource(id = destino.nombreId)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = destino.nombreId)
                        )
                    }
                )
            }

            Spacer(Modifier.weight(1f))
        }

        Navegacion(navController = navController)
    }
}