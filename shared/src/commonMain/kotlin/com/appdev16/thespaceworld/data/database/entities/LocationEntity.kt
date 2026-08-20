package com.appdev16.thespaceworld.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey val id: Int,
    val url: String,
    val name: String,
    val active: Boolean,
    val description: String?,
    val longitude: Double?,
    val latitude: Double?,
    val timezoneName: String?,
    val totalLaunchCount: Int,
    val totalLandingCount: Int,
    val mapImage: String?,
    val countryName: String?,
    val celestialBodyName: String?,
    @Embedded(prefix = "image_") val image: ImageEntity?
)
