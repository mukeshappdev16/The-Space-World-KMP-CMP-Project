package com.appdev16.thespaceworld.domain.modal.spacecrafts

import com.appdev16.thespaceworld.domain.modal.launches.Image

data class SpacecraftConfig(
    val id: Int,
    val url: String,
    val name: String,
    val type: SpacecraftType?,
    val agency: SpacecraftAgency?,
    val inUse: Boolean,
    val image: Image?
)

data class SpacecraftType(
    val id: Int,
    val name: String
)

data class SpacecraftAgency(
    val id: Int,
    val name: String,
    val abbrev: String
)
