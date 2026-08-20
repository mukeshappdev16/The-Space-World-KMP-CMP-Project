package com.appdev16.thespaceworld.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spacecrafts")
data class SpacecraftEntity(
    @PrimaryKey val id: Int,
    val url: String,
    val name: String,
    val inUse: Boolean,
    @Embedded(prefix = "type_") val type: SpacecraftTypeEntity?,
    @Embedded(prefix = "agency_") val agency: SpacecraftAgencyEntity?,
    @Embedded(prefix = "image_") val image: ImageEntity?
)

data class SpacecraftTypeEntity(
    val typeId: Int,
    val name: String
)

data class SpacecraftAgencyEntity(
    val agencyId: Int,
    val name: String,
    val abbrev: String
)
