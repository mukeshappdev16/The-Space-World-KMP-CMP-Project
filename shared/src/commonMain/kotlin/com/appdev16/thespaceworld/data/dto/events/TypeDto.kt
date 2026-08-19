package com.appdev16.thespaceworld.data.dto.events


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = ""
)