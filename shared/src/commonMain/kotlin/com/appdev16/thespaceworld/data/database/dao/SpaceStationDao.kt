package com.appdev16.thespaceworld.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appdev16.thespaceworld.data.database.entities.SpaceStationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpaceStationDao {
    @Query("SELECT * FROM space_stations ORDER BY founded DESC")
    fun getSpaceStations(): Flow<List<SpaceStationEntity>>

    @Query("SELECT * FROM space_stations WHERE id = :id")
    fun getSpaceStationById(id: Int): Flow<SpaceStationEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpaceStations(stations: List<SpaceStationEntity>)

    @Query("DELETE FROM space_stations")
    suspend fun clearAll()
}
