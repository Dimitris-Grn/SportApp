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
import com.google.gson.internal.LinkedTreeMap

@Composable
fun KaizenApp(modifier: Modifier) {

    val viewModel: SportEventViewModel = hiltViewModel()
    Column(modifier.fillMaxSize()) {
        val uiState = viewModel.uiState.collectAsState().value

        when (uiState) {
            is SportUI.Loading -> Text(text = "Loading")
            is SportUI.Error -> Text(text = "Error")
            is SportUI.Success -> {

                LazyColumn {
                    val sportEventList = uiState.sportEvent
                }
            }

        }
    }
}


@Composable
fun sportEventItem(sportEventItem: SportEvent) {

}

