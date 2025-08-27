package com.daniel.compose.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.daniel.compose.ui.navigation.DestinosNavegacion

@Composable
fun BarraNavegacion(navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        DestinosNavegacion.lista.forEach { destino ->
            val seleccionado =
                currentDestination?.hierarchy?.any { it.hasRoute(route = destino.ruta::class) } == true

            NavigationBarItem(
                selected = seleccionado,
                onClick = {
                    if (!seleccionado) {
                        navController.navigate(route = destino.ruta) {
                            popUpTo(id = navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (seleccionado) destino.iconoSeleccionado else destino.icono,
                        contentDescription = destino.nombre
                    )
                },
                label = {
                    Text(text = destino.nombre)
                }
            )
        }
    }
}