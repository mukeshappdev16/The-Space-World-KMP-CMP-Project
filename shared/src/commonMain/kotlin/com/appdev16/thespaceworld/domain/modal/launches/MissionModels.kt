package com.appdev16.thespaceworld.domain.modal.launches

data class Mission(
    val agencies: List<LaunchServiceProvider>,
    val description: String,
    val id: Int,
    val image: String?,
    val infoUrls: List<String?>,
    val name: String,
    val orbit: Orbit,
    val type: String,
    val vidUrls: List<String?>
)

data class Orbit(
    val abbrev: String,
    val celestialBody: CelestialBody,
    val id: Int,
    val name: String
)

data class CelestialBody(
    val atmosphere: Boolean? = null,
    val description: String? = null,
    val diameter: Double? = null,
    val failedLandings: Int? = null,
    val failedLaunches: Int? = null,
    val gravity: Double? = null,
    val id: Int,
    val image: Image? = null,
    val lengthOfDay: String? = null,
    val mass: Double? = null,
    val name: String,
    val responseMode: String,
    val successfulLandings: Int? = null,
    val successfulLaunches: Int? = null,
    val totalAttemptedLandings: Int? = null,
    val totalAttemptedLaunches: Int? = null,
    val type: Type? = null,
    val wikiUrl: String? = null
)
