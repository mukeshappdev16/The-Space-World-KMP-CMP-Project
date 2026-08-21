package com.appdev16.thespaceworld.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseBuilder {
    actual fun setup(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "space_world.db")
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath,
            factory = { AppDatabaseConstructor.initialize() }
        )
    }
}
