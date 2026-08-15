package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("active") val active: Boolean = false,
    @SerialName("celestial_body") val celestialBody: CelestialBodyDtoX = CelestialBodyDtoX(),
    @SerialName("country") val country: CountryDto = CountryDto(),
    @SerialName("description") val description: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("image") val image: ImageDtoXX = ImageDtoXX(),
    @SerialName("latitude") val latitude: Double = 0.0,
    @SerialName("longitude") val longitude: Double = 0.0,
    @SerialName("map_image") val mapImage: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("response_mode") val responseMode: String = "",
    @SerialName("timezone_name") val timezoneName: String = "",
    @SerialName("total_landing_count") val totalLandingCount: Int = 0,
    @SerialName("total_launch_count") val totalLaunchCount: Int = 0,
    @SerialName("url") val url: String = ""
)