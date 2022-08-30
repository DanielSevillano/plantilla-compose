package com.daniel.plantillacompose.ui.theme

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.daniel.plantillacompose.R

sealed class Destino(
    val ruta: String,
    @StringRes val nombreId: Int,
    val icono: ImageVector,
    val iconoSeleccionado: ImageVector
) {
    object Destino1 : Destino("destino1", R.string.destino1, Icons.Outlined.Home, Icons.Filled.Home)
    object Destino2 :
        Destino("destino2", R.string.destino2, Icons.Outlined.CheckCircle, Icons.Filled.CheckCircle)

    object Destino3 : Destino(
        "destino3",
        R.string.destino3,
        Icons.Outlined.AccountCircle,
        Icons.Filled.AccountCircle
    )
}

val destinos = listOf(
    Destino.Destino1,
    Destino.Destino2,
    Destino.Destino3
)