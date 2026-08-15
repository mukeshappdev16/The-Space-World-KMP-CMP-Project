package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CelestialBodyDtoX(
    @SerialName("atmosphere") val atmosphere: Boolean = false,
    @SerialName("description") val description: String = "",
    @SerialName("diameter") val diameter: Double = 0.0,
    @SerialName("failed_landings") val failedLandings: Int = 0,
    @SerialName("failed_launches") val failedLaunches: Int = 0,
    @SerialName("gravity") val gravity: Double = 0.0,
    @SerialName("id") val id: Int = 0,
    @SerialName("image") val image: ImageDtoXX = ImageDtoXX(),
    @SerialName("length_of_day") val lengthOfDay: String = "",
    @SerialName("mass") val mass: Double = 0.0,
    @SerialName("name") val name: String = "",
    @SerialName("response_mode") val responseMode: String = "",
    @SerialName("successful_landings") val successfulLandings: Int = 0,
    @SerialName("successful_launches") val successfulLaunches: Int = 0,
    @SerialName("total_attempted_landings") val totalAttemptedLandings: Int = 0,
    @SerialName("total_attempted_launches") val totalAttemptedLaunches: Int = 0,
    @SerialName("type") val type: TypeDto = TypeDto(),
    @SerialName("wiki_url") val wikiUrl: String = ""
)