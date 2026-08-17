package com.appdev16.thespaceworld.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.appdev16.thespaceworld.data.database.dao.LaunchDao
import com.appdev16.thespaceworld.data.database.entities.LaunchEntity

@Database(entities = [LaunchEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchDao(): LaunchDao
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(true)
        .build()
}
