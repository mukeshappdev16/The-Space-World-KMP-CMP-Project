package com.appdev16.thespaceworld.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appdev16.thespaceworld.data.database.entities.AgencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgencyDao {
    @Query("SELECT * FROM agencies ORDER BY name ASC")
    fun getAgencies(): Flow<List<AgencyEntity>>

    @Query("SELECT * FROM agencies WHERE id = :id")
    fun getAgencyById(id: Int): Flow<AgencyEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgencies(agencies: List<AgencyEntity>)

    @Query("DELETE FROM agencies")
    suspend fun clearAll()
    
    @Query("SELECT COUNT(*) FROM agencies")
    suspend fun getCount(): Int
}
