package com.appdev16.thespaceworld.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

actual class DatabaseBuilder {
    actual fun setup(): RoomDatabase.Builder<AppDatabase> {
        val dbFilePath = NSHomeDirectory() + "/space_world.db"
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath,
            factory = { AppDatabaseConstructor.initialize() }
        )
    }
}
