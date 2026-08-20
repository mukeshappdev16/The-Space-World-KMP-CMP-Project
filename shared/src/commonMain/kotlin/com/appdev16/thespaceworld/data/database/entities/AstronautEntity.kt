package com.appdev16.thespaceworld.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "astronauts")
data class AstronautEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val inSpace: Boolean,
    val timeInSpace: String,
    val evaTime: String,
    val age: Int,
    val dateOfBirth: String,
    val bio: String,
    val flightsCount: Int,
    val landingsCount: Int,
    val spacewalksCount: Int,
    
    @Embedded(prefix = "status_") val status: AstronautStatusEntity?,
    @Embedded(prefix = "agency_") val agency: AstronautAgencyEntity?,
    @Embedded(prefix = "image_") val image: AstronautImageEntity?
)

data class AstronautStatusEntity(
    val statusId: Int,
    val name: String
)

data class AstronautAgencyEntity(
    val agencyId: Int,
    val name: String,
    val abbrev: String
)

data class AstronautImageEntity(
    val imageUrl: String,
    val name: String,
    val thumbnailUrl: String
)
