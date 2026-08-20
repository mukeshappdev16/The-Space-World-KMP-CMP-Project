package com.appdev16.thespaceworld.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.appdev16.thespaceworld.data.database.converters.RoomConverters
import com.appdev16.thespaceworld.data.database.dao.AgencyDao
import com.appdev16.thespaceworld.data.database.dao.AstronautDao
import com.appdev16.thespaceworld.data.database.dao.EventDao
import com.appdev16.thespaceworld.data.database.dao.LaunchDao
import com.appdev16.thespaceworld.data.database.dao.LocationDao
import com.appdev16.thespaceworld.data.database.dao.SpaceStationDao
import com.appdev16.thespaceworld.data.database.dao.SpacecraftDao
import com.appdev16.thespaceworld.data.database.entities.AgencyEntity
import com.appdev16.thespaceworld.data.database.entities.AstronautEntity
import com.appdev16.thespaceworld.data.database.entities.EventEntity
import com.appdev16.thespaceworld.data.database.entities.LaunchEntity
import com.appdev16.thespaceworld.data.database.entities.LocationEntity
import com.appdev16.thespaceworld.data.database.entities.SpaceStationEntity
import com.appdev16.thespaceworld.data.database.entities.SpacecraftEntity

@Database(entities = [LaunchEntity::class, EventEntity::class, AgencyEntity::class, AstronautEntity::class, SpaceStationEntity::class, SpacecraftEntity::class, LocationEntity::class], version = 4)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchDao(): LaunchDao
    abstract fun eventDao(): EventDao
    abstract fun agencyDao(): AgencyDao
    abstract fun astronautDao(): AstronautDao
    abstract fun spaceStationDao(): SpaceStationDao
    abstract fun spacecraftDao(): SpacecraftDao
    abstract fun locationDao(): LocationDao
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(true)
        .build()
}
