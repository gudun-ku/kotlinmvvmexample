package com.beloushkin.test.mymvvmapp.ui.main

import kotlinx.coroutines.delay

class ProfileRepositoryImplementationFaked: ProfileViewModel.Repository {
    override suspend fun loadProfile(): Profile {
        delay(1_000L)
        return Profile(
            id = 1,
            name = "User",
            email = "example@email.com"
        )
    }

    override suspend fun logout() {
        delay(1_000L)
        throw Throwable("It's a fake method")
    }
}