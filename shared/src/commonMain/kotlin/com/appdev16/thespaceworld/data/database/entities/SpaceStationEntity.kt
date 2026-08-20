package com.appdev16.thespaceworld.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "space_stations")
data class SpaceStationEntity(
    @PrimaryKey val id: Int,
    val url: String,
    val name: String,
    val founded: String,
    val deorbited: String?,
    val description: String,
    val orbit: String,
    @Embedded(prefix = "status_") val status: StatusEntity?,
    @Embedded(prefix = "type_") val type: StationTypeEntity?,
    @Embedded(prefix = "image_") val image: ImageEntity?,
    val ownersJson: String // Serialized list of owners for simplicity if needed, or just store a few
)

data class StationTypeEntity(
    val typeId: Int,
    val name: String
)

data class StationOwnerEntity(
    val ownerId: Int,
    val name: String,
    val abbrev: String
)
