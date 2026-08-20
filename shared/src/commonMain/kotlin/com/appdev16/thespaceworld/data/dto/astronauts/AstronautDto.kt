package com.appdev16.thespaceworld.data.dto.astronauts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AstronautDto(
    @SerialName("id") val id: Int,
    @SerialName("url") val url: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("status") val status: AstronautStatusDto? = AstronautStatusDto(),
    @SerialName("agency") val agency: AstronautAgencyDto? = AstronautAgencyDto(),
    @SerialName("image") val image: AstronautImageDto? = AstronautImageDto(),
    @SerialName("response_mode") val responseMode: String = "",
    @SerialName("type") val type: AstronautTypeDto? = AstronautTypeDto(),
    @SerialName("in_space") val inSpace: Boolean = false,
    @SerialName("time_in_space") val timeInSpace: String = "",
    @SerialName("eva_time") val evaTime: String = "",
    @SerialName("age") val age: Int = 0,
    @SerialName("date_of_birth") val dateOfBirth: String = "",
    @SerialName("date_of_death") val dateOfDeath: String? = null,
    @SerialName("nationality") val nationality: List<NationalityDto> = emptyList(),
    @SerialName("bio") val bio: String = "",
    @SerialName("wiki") val wiki: String? = null,
    @SerialName("last_flight") val lastFlight: String? = null,
    @SerialName("first_flight") val firstFlight: String? = null,
    @SerialName("flights_count") val flightsCount: Int = 0,
    @SerialName("landings_count") val landingsCount: Int = 0,
    @SerialName("spacewalks_count") val spacewalksCount: Int = 0
)

@Serializable
data class AstronautStatusDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = ""
)

@Serializable
data class AstronautTypeDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = ""
)

@Serializable
data class AstronautAgencyDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("url") val url: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("abbrev") val abbrev: String = ""
)

@Serializable
data class AstronautImageDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("image_url") val imageUrl: String = "",
    @SerialName("thumbnail_url") val thumbnailUrl: String = ""
)

@Serializable
data class NationalityDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("alpha_2_code") val alpha2Code: String = "",
    @SerialName("alpha_3_code") val alpha3Code: String = "",
    @SerialName("nationality_name") val nationalityName: String = ""
)
