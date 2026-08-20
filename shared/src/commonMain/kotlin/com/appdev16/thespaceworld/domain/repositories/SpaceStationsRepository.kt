package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result

interface SpaceStationsRepository {
    suspend fun getSpaceStations(limit: Int, offset: Int): Result<List<SpaceStation>, NetworkError>
    suspend fun getSpaceStationDetail(id: Int): Result<SpaceStation, NetworkError>
}
