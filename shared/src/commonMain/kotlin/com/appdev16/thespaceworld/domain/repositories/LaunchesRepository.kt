package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result

interface LaunchesRepository {
    suspend fun getSpaceLaunches(): Result<List<Launch>, NetworkError>
}
