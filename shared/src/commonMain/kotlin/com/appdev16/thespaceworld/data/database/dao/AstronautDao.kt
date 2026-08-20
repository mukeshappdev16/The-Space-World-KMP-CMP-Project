package com.appdev16.thespaceworld.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appdev16.thespaceworld.data.database.entities.AstronautEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AstronautDao {
    @Query("SELECT * FROM astronauts")
    fun getAstronauts(): Flow<List<AstronautEntity>>

    @Query("SELECT * FROM astronauts WHERE id = :id")
    fun getAstronautById(id: Int): Flow<AstronautEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAstronauts(astronauts: List<AstronautEntity>)

    @Query("DELETE FROM astronauts")
    suspend fun clearAll()
}
