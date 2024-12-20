package com.example.kaizenapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            sportEventRepository.testFetchSportEvents()
//            sportEventRepository.fetchSportEvents().collect { response ->
//                _uiState.value = when (response) {
//                    is NetworkResult.Error -> SportUI.Error("Error")
//                    is NetworkResult.Exception -> SportUI.Error("Exception: ${response.e.message}")
//                    is NetworkResult.Success -> SportUI.Success(response.apiData)
//                }
//            }
        }
    }
}


sealed class SportUI {
    data object Loading : SportUI()
    data class Success(val sportEvent: String) : SportUI()
    data class Error(val error: String) : SportUI()
}

