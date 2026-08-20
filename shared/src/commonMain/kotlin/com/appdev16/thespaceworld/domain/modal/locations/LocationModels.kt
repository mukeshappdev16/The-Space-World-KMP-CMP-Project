package com.appdev16.thespaceworld.domain.modal.locations

import com.appdev16.thespaceworld.domain.modal.launches.Image

data class Location(
    val id: Int,
    val url: String,
    val name: String,
    val active: Boolean,
    val description: String?,
    val longitude: Double?,
    val latitude: Double?,
    val timezoneName: String?,
    val totalLaunchCount: Int,
    val totalLandingCount: Int,
    val mapImage: String?,
    val image: Image?,
    val countryName: String?,
    val celestialBodyName: String?
)
