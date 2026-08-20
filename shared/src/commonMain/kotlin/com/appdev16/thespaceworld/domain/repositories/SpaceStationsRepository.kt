package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

interface SpaceStationsRepository {
    fun getSpaceStations(): Flow<List<SpaceStation>>
    fun getSpaceStationDetail(id: Int): Flow<SpaceStation?>
    suspend fun syncSpaceStations(limit: Int, offset: Int): Result<Unit, NetworkError>
}
