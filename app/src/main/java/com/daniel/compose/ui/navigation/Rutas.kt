package com.daniel.compose.ui.navigation

import kotlinx.serialization.Serializable

sealed class Rutas {
    @Serializable
    data object Destino1

    @Serializable
    data object Destino2

    @Serializable
    data object Destino3
}