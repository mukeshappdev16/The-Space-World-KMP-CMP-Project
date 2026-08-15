package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MissionDto(
    @SerialName("agencies") val agencies: List<LaunchServiceProviderDto> = listOf(),
    @SerialName("description") val description: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("image") val image: String? = null,
    @SerialName("info_urls") val infoUrls: List<String?> = listOf(),
    @SerialName("name") val name: String = "",
    @SerialName("orbit") val orbit: OrbitDto = OrbitDto(),
    @SerialName("type") val type: String = "",
    @SerialName("vid_urls") val vidUrls: List<String?> = listOf()
)