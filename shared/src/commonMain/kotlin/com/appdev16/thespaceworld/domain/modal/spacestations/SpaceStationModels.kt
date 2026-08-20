package com.appdev16.thespaceworld.domain.modal.spacestations

import com.appdev16.thespaceworld.domain.modal.launches.Image
import com.appdev16.thespaceworld.domain.modal.launches.Status

data class SpaceStation(
    val id: Int,
    val url: String,
    val name: String,
    val status: Status?,
    val founded: String,
    val deorbited: String?,
    val description: String,
    val orbit: String,
    val type: StationType?,
    val image: Image?,
    val owners: List<StationOwner>
)

data class StationType(
    val id: Int,
    val name: String
)

data class StationOwner(
    val id: Int,
    val name: String,
    val abbrev: String
)
