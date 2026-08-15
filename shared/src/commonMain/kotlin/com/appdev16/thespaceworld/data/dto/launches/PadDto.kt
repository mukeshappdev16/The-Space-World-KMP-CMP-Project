package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PadDto(
    @SerialName("active") val active: Boolean = false,
    @SerialName("agencies") val agencies: List<LaunchServiceProviderDto> = listOf(),
    @SerialName("country") val country: CountryDto = CountryDto(),
    @SerialName("description") val description: String? = null,
    @SerialName("fastest_turnaround") val fastestTurnaround: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("image") val image: String? = null,
    @SerialName("info_url") val infoUrl: String? = null,
    @SerialName("latitude") val latitude: Double = 0.0,
    @SerialName("location") val location: LocationDto = LocationDto(),
    @SerialName("longitude") val longitude: Double = 0.0,
    @SerialName("map_image") val mapImage: String = "",
    @SerialName("map_url") val mapUrl: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("orbital_launch_attempt_count") val orbitalLaunchAttemptCount: Int = 0,
    @SerialName("total_launch_count") val totalLaunchCount: Int = 0,
    @SerialName("url") val url: String = "",
    @SerialName("wiki_url") val wikiUrl: String = ""
)