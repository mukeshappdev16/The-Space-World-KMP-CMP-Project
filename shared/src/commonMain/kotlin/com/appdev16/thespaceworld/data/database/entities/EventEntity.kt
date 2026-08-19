package com.appdev16.thespaceworld.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val location: String,
    val date: String,
    val lastUpdated: String,
    val webcastLive: Boolean,
    val url: String,
    val slug: String,
    val responseMode: String,
    
    @Embedded(prefix = "date_precision_") val datePrecision: EventDatePrecisionEntity?,
    @Embedded(prefix = "image_") val image: EventImageEntity?,
    @Embedded(prefix = "type_") val type: EventTypeEntity?
)

data class EventDatePrecisionEntity(
    val abbrev: String,
    val description: String,
    val precisionId: Int,
    val name: String
)

data class EventImageEntity(
    val imageUrl: String,
    val name: String,
    val thumbnailUrl: String
)

data class EventTypeEntity(
    val typeId: Int,
    val name: String
)
