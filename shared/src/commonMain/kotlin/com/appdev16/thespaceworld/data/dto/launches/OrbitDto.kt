package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrbitDto(
    @SerialName("abbrev") val abbrev: String = "",
    @SerialName("celestial_body") val celestialBody: CelestialBodyDto = CelestialBodyDto(),
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = ""
)