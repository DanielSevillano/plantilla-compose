package com.daniel.plantillacompose

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
    @StringRes val nombreId: Int,
    val icono: ImageVector,
    val iconoSeleccionado: ImageVector
) {
    Destino1("destino1", R.string.destino1, Icons.Outlined.Home, Icons.Filled.Home),
    Destino2("destino2", R.string.destino2, Icons.Outlined.CheckCircle, Icons.Filled.CheckCircle),
    Destino3(
        "destino3",
        R.string.destino3,
        Icons.Outlined.AccountCircle,
        Icons.Filled.AccountCircle
    )
}