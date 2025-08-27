package com.daniel.compose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home

object DestinosNavegacion {
    val lista = listOf(
        DestinoNavegacion(
            ruta = Rutas.Destino1,
            nombre = "Destino 1",
            icono = Icons.Outlined.Home,
            iconoSeleccionado = Icons.Filled.Home
        ),
        DestinoNavegacion(
            ruta = Rutas.Destino2,
            nombre = "Destino 2",
            icono = Icons.Outlined.CheckCircle,
            iconoSeleccionado = Icons.Filled.CheckCircle
        ),
        DestinoNavegacion(
            ruta = Rutas.Destino3,
            nombre = "Destino 3",
            icono = Icons.Outlined.AccountCircle,
            iconoSeleccionado = Icons.Filled.AccountCircle
        )
    )
}