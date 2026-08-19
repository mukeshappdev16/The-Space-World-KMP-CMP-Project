package com.appdev16.thespaceworld.data.dto.events


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    @SerialName("credit") val credit: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("image_url") val imageUrl: String = "",
    @SerialName("license") val license: LicenseDto = LicenseDto(),
    @SerialName("name") val name: String = "",
    @SerialName("single_use") val singleUse: Boolean = false,
    @SerialName("thumbnail_url") val thumbnailUrl: String = ""
)