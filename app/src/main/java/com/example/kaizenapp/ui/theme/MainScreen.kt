package com.example.kaizenapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kaizenapp.data.model.ActiveEvents
import com.google.gson.internal.LinkedTreeMap

@Composable
fun KaizenApp(modifier: Modifier) {
    val viewModel: SportEventViewModel = hiltViewModel()
    Column(modifier.fillMaxSize()) {
        when (val uiState = viewModel.uiState.collectAsState().value) {
            is SportUI.Loading -> Text(text = "Loading")
            is SportUI.Error -> Text(text = "Error")
            is SportUI.Success -> {
                LazyColumn() {
                    items(items = uiState.sportEvent,
                        key = { sportEvent -> sportEvent["i"] as String }) { sportEvent ->
                        val title = sportEvent["d"] as String
                        Text(text = title)

                        val arrayEvents: ArrayList<LinkedTreeMap<String, ActiveEvents>> =
                            sportEvent.get("e") as ArrayList<LinkedTreeMap<String, ActiveEvents>>

//                              items(
//                                items = arrayEvents,
//                                key = { arrayKey -> arrayKey["i"] as String }) { event ->
//                                val time = event["tt"] as Double
//                                val competitors = event["d"] as String
//                                val splitCompetitors = competitors.split("-")
//                                Column(Modifier.padding(10.dp)) {
//                                    Text(text = "12:35:45")
//                                    Checkbox(checked = false, onCheckedChange = null)
//                                    Text(text = splitCompetitors[0])
//                                    Text("vs")
//                                    Text(text = splitCompetitors[1])
//                                }
//                            }
                    }

                }
            }
        }

    }
}


@Composable
fun EventItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "15:00:15", style = MaterialTheme.typography.bodySmall
            )
            Checkbox(checked = true, onCheckedChange = null)
            Text(
                text = "Competitor 1", style = MaterialTheme.typography.bodySmall
            )
            Text(
                color = Color.Red, text = "vs"
            )
            Text(
                text = "Competitor 2", style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun LazySportEventGrid() {
    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp), content = {
        item(
            span = { GridItemSpan(maxLineSpan) }) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Sport 1")
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    Checkbox(checked = false, onCheckedChange = null)
                    Checkbox(checked = false, onCheckedChange = null)
                    Text("Collapse")
                }
            }
        }
        items(10) { index ->
            EventItem()
        }
    })
}


@Preview(showBackground = true)
@Composable
fun PreviewEventItemPreview() {
    KaizenAppTheme {
        EventItem()
    }
}

@Preview(showBackground = true)
@Composable
fun MyLazyVerticalGridPreview() {
    KaizenAppTheme {
        LazySportEventGrid()
    }
}

