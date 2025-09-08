package com.daniel.compose.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import soup.compose.material.motion.animation.materialFadeThroughIn
import soup.compose.material.motion.animation.materialFadeThroughOut

@Composable
fun Navegacion(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Rutas.Destino1,
        enterTransition = { materialFadeThroughIn() },
        exitTransition = { materialFadeThroughOut() }
    ) {
        composable<Rutas.Destino1> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Destino 1")
            }
        }

        composable<Rutas.Destino2> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Destino 2")
            }
        }

        composable<Rutas.Destino3> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Destino 3")
            }
        }
    }
}