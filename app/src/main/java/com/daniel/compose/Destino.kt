package com.daniel.compose

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destino(
    val ruta: String,
    @StringRes val nombre: Int,
    val icono: ImageVector,
    val iconoSeleccionado: ImageVector
) {
    Destino1(
        ruta = "destino1",
        nombre = R.string.destino1,
        icono = Icons.Outlined.Home,
        iconoSeleccionado = Icons.Filled.Home
    ),

    Destino2(
        ruta = "destino2",
        nombre = R.string.destino2,
        icono = Icons.Outlined.CheckCircle,
        iconoSeleccionado = Icons.Filled.CheckCircle
    ),

    Destino3(
        ruta = "destino3",
        nombre = R.string.destino3,
        icono = Icons.Outlined.AccountCircle,
        iconoSeleccionado = Icons.Filled.AccountCircle
    )
}