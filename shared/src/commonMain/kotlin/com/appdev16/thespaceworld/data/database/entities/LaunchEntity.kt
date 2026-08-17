package com.appdev16.thespaceworld.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launches")
data class LaunchEntity(
    @PrimaryKey val id: String,
    val name: String,
    val net: String,
    val lastUpdated: String,
    val imageUrl: String?,
    val providerName: String?,
    val statusName: String?,
    val missionName: String?,
    val missionDescription: String?,
    val webcastLive: Boolean,
    val windowEnd: String,
    val windowStart: String
)
