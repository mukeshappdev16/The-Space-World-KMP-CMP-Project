package com.appdev16.thespaceworld.data.dto.astronauts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AstronautResponseDto(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<AstronautDto>
)
