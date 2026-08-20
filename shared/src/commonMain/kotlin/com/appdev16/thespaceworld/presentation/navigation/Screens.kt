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
    data object Events : Screen
    
    @Serializable
    data class LaunchDetail(val id: String) : Screen
    
    @Serializable
    data class EventDetail(val id: Int) : Screen
    
    @Serializable
    data object Agencies : Screen
    
    @Serializable
    data class AgencyDetail(val id: Int) : Screen

    @Serializable
    data object Astronauts : Screen

    @Serializable
    data class AstronautDetail(val id: Int) : Screen

    @Serializable
    data object SpaceStations : Screen

    @Serializable
    data class SpaceStationDetail(val id: Int) : Screen
}
