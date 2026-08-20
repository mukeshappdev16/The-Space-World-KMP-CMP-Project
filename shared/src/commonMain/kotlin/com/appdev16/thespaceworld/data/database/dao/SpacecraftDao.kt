package com.appdev16.thespaceworld.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appdev16.thespaceworld.data.database.entities.SpacecraftEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpacecraftDao {
    @Query("SELECT * FROM spacecrafts ORDER BY name ASC")
    fun getSpacecrafts(): Flow<List<SpacecraftEntity>>

    @Query("SELECT * FROM spacecrafts WHERE id = :id")
    fun getSpacecraftById(id: Int): Flow<SpacecraftEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpacecrafts(spacecrafts: List<SpacecraftEntity>)

    @Query("DELETE FROM spacecrafts")
    suspend fun clearAll()
}
