package com.appdev16.thespaceworld.data.dto.launches


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    @SerialName("image_url") val imageUrl: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("thumbnail_url") val thumbnailUrl: String = ""
)