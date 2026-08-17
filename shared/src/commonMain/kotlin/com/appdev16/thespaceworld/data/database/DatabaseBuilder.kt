package com.appdev16.thespaceworld.data.database

import androidx.room.RoomDatabase

expect class DatabaseBuilder {
    fun setup(): RoomDatabase.Builder<AppDatabase>
}
