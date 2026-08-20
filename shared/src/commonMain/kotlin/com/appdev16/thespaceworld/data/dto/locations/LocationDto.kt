package com.appdev16.thespaceworld.data.dto.locations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.appdev16.thespaceworld.data.dto.launches.ImageDto

@Serializable
data class LocationResponseDto(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<LocationDto>
)

@Serializable
data class LocationDto(
    @SerialName("id") val id: Int,
    @SerialName("url") val url: String,
    @SerialName("name") val name: String,
    @SerialName("active") val active: Boolean,
    @SerialName("description") val description: String? = null,
    @SerialName("longitude") val longitude: Double? = null,
    @SerialName("latitude") val latitude: Double? = null,
    @SerialName("timezone_name") val timezoneName: String? = null,
    @SerialName("total_launch_count") val totalLaunchCount: Int = 0,
    @SerialName("total_landing_count") val totalLandingCount: Int = 0,
    @SerialName("map_image") val mapImage: String? = null,
    @SerialName("image") val image: ImageDto? = null,
    @SerialName("country") val country: CountryDto? = null,
    @SerialName("celestial_body") val celestialBody: CelestialBodyDto? = null
)

@Serializable
data class CountryDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("alpha_2_code") val alpha2Code: String? = null,
    @SerialName("alpha_3_code") val alpha3Code: String? = null
)

@Serializable
data class CelestialBodyDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null,
    @SerialName("wiki_url") val wikiUrl: String? = null
)
