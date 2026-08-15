package com.appdev16.thespaceworld.domain.modal.launches

data class Pad(
    val active: Boolean,
    val agencies: List<String?>,
    val country: Country,
    val description: String?,
    val fastestTurnaround: String,
    val id: Int,
    val image: String?,
    val infoUrl: String?,
    val latitude: Double,
    val location: Location,
    val longitude: Double,
    val mapImage: String,
    val mapUrl: String,
    val name: String,
    val orbitalLaunchAttemptCount: Int,
    val totalLaunchCount: Int,
    val url: String,
    val wikiUrl: String
)

data class Location(
    val active: Boolean,
    val celestialBody: CelestialBody,
    val country: Country,
    val description: String,
    val id: Int,
    val image: Image,
    val latitude: Double,
    val longitude: Double,
    val mapImage: String,
    val name: String,
    val responseMode: String,
    val timezoneName: String,
    val totalLandingCount: Int,
    val totalLaunchCount: Int,
    val url: String
)

data class Country(
    val alpha2Code: String,
    val alpha3Code: String,
    val id: Int,
    val name: String,
    val nationalityName: String,
    val nationalityNameComposed: String
)
