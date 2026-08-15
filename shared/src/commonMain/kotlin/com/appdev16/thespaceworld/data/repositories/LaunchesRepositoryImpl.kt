package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.remote.LaunchesRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.domain.repositories.LaunchesRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.map

class LaunchesRepositoryImpl(
    private val remoteDataSource: LaunchesRemoteDataSource
) : LaunchesRepository {
    override suspend fun getSpaceLaunches(): Result<List<Launch>, NetworkError> {
        return remoteDataSource.getLaunches().map { responseDto ->
            responseDto.results.map { it.toDomain() }
        }
    }
}
