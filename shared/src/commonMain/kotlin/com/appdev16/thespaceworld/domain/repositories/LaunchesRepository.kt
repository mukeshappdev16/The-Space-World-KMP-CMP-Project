package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.launches.Launch

interface LaunchesRepository {
    suspend fun getSpaceLaunches(): List<Launch>
}