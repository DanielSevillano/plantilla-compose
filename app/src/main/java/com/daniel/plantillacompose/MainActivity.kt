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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.daniel.plantillacompose.ui.theme.AppTheme
import com.daniel.plantillacompose.ui.theme.destinos

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val destinoActual = navBackStackEntry?.destination
                val dimensionesVentana = calculateWindowSizeClass(this)

                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text(text = destinoActual?.route.toString()) }
                        )
                    },
                    bottomBar = {
                        if (dimensionesVentana.widthSizeClass == WindowWidthSizeClass.Compact) {
                            NavigationBar {
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
        startDestination = destinos.first().ruta
    ) {
        composable("Destino 1") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Contenido 1")
            }
        }
        composable("Destino 2") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Contenido 2")
            }
        }
        composable("Destino 3") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Contenido 3")
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

            destinos.forEach { seccion ->
                val seleccionado =
                    destinoActual?.hierarchy?.any { it.route == seccion.ruta } == true
                NavigationDrawerItem(
                    selected = seleccionado,
                    onClick = {
                        navController.navigate(seccion.ruta) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (seleccionado) seccion.iconoSeleccionado else seccion.icono,
                            contentDescription = seccion.ruta,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = seccion.ruta
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
        NavigationRail(modifier = Modifier.padding(top = 4.dp)) {
            Spacer(Modifier.weight(1f))

            destinos.forEach { seccion ->
                val seleccionado =
                    destinoActual?.hierarchy?.any { it.route == seccion.ruta } == true
                NavigationRailItem(
                    selected = seleccionado,
                    onClick = {
                        navController.navigate(seccion.ruta) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (seleccionado) seccion.iconoSeleccionado else seccion.icono,
                            contentDescription = seccion.ruta,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = seccion.ruta
                        )
                    }
                )
            }

            Spacer(Modifier.weight(1f))
        }

        Navegacion(navController = navController)
    }
}