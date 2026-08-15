package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketDto(
    @SerialName("configuration") val configuration: ConfigurationDto = ConfigurationDto(),
    @SerialName("id") val id: Int = 0
)