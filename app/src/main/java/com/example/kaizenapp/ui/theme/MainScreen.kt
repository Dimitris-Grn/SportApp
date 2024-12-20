package com.example.kaizenapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kaizenapp.data.model.SportEvent

@Composable
fun KaizenApp(modifier: Modifier) {
    val viewModel: SportEventViewModel = hiltViewModel()
    viewModel.fetchSportEvent()
    val uiState = viewModel.uiState.collectAsState().value

    Column(modifier.fillMaxSize()) {
        when (uiState) {
            is SportUI.Loading -> Text(text = "Loading")
            is SportUI.Error -> Text(text = "Error")
            is SportUI.Success -> {
                LazyColumn {
//                    items(uiState.sportEvent) { item ->
//                        //Text(text = item.sportName)
//                    }
                }
            }

        }
    }
}


@Composable
fun sportEventItem(sportEventItem: SportEvent) {

}

