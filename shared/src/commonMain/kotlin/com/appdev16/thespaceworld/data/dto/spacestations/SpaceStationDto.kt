package com.appdev16.thespaceworld.data.dto.spacestations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.appdev16.thespaceworld.data.dto.launches.ImageDto
import com.appdev16.thespaceworld.data.dto.launches.StatusDto

@Serializable
data class SpaceStationResponseDto(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<SpaceStationDto>
)

@Serializable
data class SpaceStationDto(
    @SerialName("id") val id: Int,
    @SerialName("url") val url: String,
    @SerialName("name") val name: String,
    @SerialName("status") val status: StatusDto?,
    @SerialName("founded") val founded: String,
    @SerialName("deorbited") val deorbited: String?,
    @SerialName("description") val description: String,
    @SerialName("orbit") val orbit: String,
    @SerialName("type") val type: StationTypeDto?,
    @SerialName("image") val image: ImageDto?,
    @SerialName("owners") val owners: List<StationOwnerDto> = emptyList()
)

@Serializable
data class StationTypeDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)

@Serializable
data class StationOwnerDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("abbrev") val abbrev: String
)
