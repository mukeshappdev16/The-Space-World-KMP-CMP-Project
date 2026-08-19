package com.appdev16.thespaceworld.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agencies")
data class AgencyEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val featured: Boolean,
    val countryCode: String?,
    val abbrev: String?,
    val description: String?,
    val administrator: String?,
    val foundingYear: String?,
    val totalLaunchCount: Int,
    val successfulLaunches: Int,
    val failedLaunches: Int,
    val pendingLaunches: Int,
    val infoUrl: String?,
    val wikiUrl: String?,
    
    @Embedded(prefix = "type_") val type: AgencyTypeEntity?,
    @Embedded(prefix = "image_") val image: AgencyImageEntity?,
    @Embedded(prefix = "logo_") val logo: AgencyImageEntity?
)

data class AgencyTypeEntity(
    val typeId: Int,
    val name: String
)

data class AgencyImageEntity(
    val imageId: Int,
    val imageUrl: String,
    val name: String,
    val thumbnailUrl: String
)
