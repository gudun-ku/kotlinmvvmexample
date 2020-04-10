package com.beloushkin.test.mymvvmapp.ui.main

import kotlinx.coroutines.delay

class ProfileRepositoryImplementation(
    private val api: ProfileApi
): ProfileViewModel.Repository {
    override suspend fun loadProfile(): Profile = api.loadProfile()

    override suspend fun logout() = api.logout()
}