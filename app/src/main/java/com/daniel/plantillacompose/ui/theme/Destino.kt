package com.daniel.plantillacompose.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

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