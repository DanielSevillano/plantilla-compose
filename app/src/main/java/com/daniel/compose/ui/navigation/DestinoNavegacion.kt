package com.daniel.compose.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class DestinoNavegacion<T : Any>(
    val ruta: T,
    val nombre: String,
    val icono: ImageVector,
    val iconoSeleccionado: ImageVector
)