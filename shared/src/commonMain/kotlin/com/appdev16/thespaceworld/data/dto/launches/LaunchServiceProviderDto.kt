package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchServiceProviderDto(
    @SerialName("abbrev") val abbrev: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("response_mode") val responseMode: String = "",
    @SerialName("type") val type: TypeDto = TypeDto(),
    @SerialName("url") val url: String = ""
)