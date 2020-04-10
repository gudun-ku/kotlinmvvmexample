package com.beloushkin.test.mymvvmapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: Repository): ViewModel() {
    // mutableLivedata from profile private - no setter outside
    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> = _profile
    // for exceptions
    private val _exception = MutableLiveData<Throwable>()
    val exception: LiveData<Throwable> = _exception
    // for navigate to start page
    private val _needNavigateToStart = MutableLiveData<Boolean>(false)
    val needNavigateToStart:LiveData<Boolean> = _needNavigateToStart

    init {
        // standard viewmodel coroutine scope
        viewModelScope.launch {
            try {
                val model = repository.loadProfile()
                _profile.postValue(model)
            } catch (t: Throwable) {
                _exception.postValue(t)
            }
        }
    }

    fun logout() = viewModelScope.launch {
        try {
            repository.logout()
            _needNavigateToStart.postValue(true)
        } catch (t: Throwable) {
            _exception.postValue(t)
        }
    }

    interface Repository {
        suspend fun loadProfile(): Profile
        suspend fun logout()
    }
}

