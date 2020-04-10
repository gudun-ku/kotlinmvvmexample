package com.beloushkin.test.mymvvmapp.ui.main

interface ProfileApi {
    suspend fun loadProfile(): Profile
    suspend fun logout()
}