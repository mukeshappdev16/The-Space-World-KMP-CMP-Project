package com.appdev16.thespaceworld.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Splash : Screen
    
    @Serializable
    data object Home : Screen
    
    @Serializable
    data object Launches : Screen
    
    @Serializable
    data class LaunchDetail(val id: String) : Screen
}
