package com.example.kaizenapp.ui.theme

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaizenapp.data.datasources.SportEventListRemoteDataSource
import com.example.kaizenapp.data.model.SportEvent
import com.example.kaizenapp.data.repositories.SportEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportEventViewModel @Inject constructor(
    private val sportEventRepository: SportEventRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<SportUI>(SportUI.Loading)
    val uiState: StateFlow<SportUI> = _uiState.asStateFlow()

    fun fetchSportEvent() {
        viewModelScope.launch {
            sportEventRepository.testFetchSportEvents().collect({ response ->
                _uiState.value = when (response) {
                    is SportEventListRemoteDataSource.NetworkResult.Error -> {
                        SportUI.Error("SOmethig gone wrong")
                    }
                    is SportEventListRemoteDataSource.NetworkResult.Success -> {
                        val listSportEvent : ArrayList<SportEvent> = response.apiData as ArrayList<SportEvent>
                        SportUI.Success(listSportEvent)
                    }
                }
            })
        }
    }
}


sealed class SportUI {
    data object Loading : SportUI()
    data class Success(val sportEvent: ArrayList<SportEvent>) : SportUI()
    data class Error(val error: String) : SportUI()
}

