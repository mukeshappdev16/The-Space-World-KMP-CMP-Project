package com.appdev16.thespaceworld.data.dto.spacecrafts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.appdev16.thespaceworld.data.dto.launches.ImageDto

@Serializable
data class SpacecraftResponseDto(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<SpacecraftDto>
)

@Serializable
data class SpacecraftDto(
    @SerialName("id") val id: Int,
    @SerialName("url") val url: String,
    @SerialName("name") val name: String,
    @SerialName("type") val type: SpacecraftTypeDto?,
    @SerialName("agency") val agency: SpacecraftAgencyDto?,
    @SerialName("in_use") val inUse: Boolean,
    @SerialName("image") val image: ImageDto?
)

@Serializable
data class SpacecraftTypeDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)

@Serializable
data class SpacecraftAgencyDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("abbrev") val abbrev: String
)
