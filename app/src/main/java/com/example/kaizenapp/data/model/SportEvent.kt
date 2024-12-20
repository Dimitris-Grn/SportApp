package com.example.kaizenapp.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class SportEvent(
    @SerializedName("d") val sport: String, // Maps "d" to "sport"
    @SerializedName("e") val events: List<Event>? = null, // Maps "e" to "events"
    @SerializedName("i") val id: String // Maps "i" to "id"
)

data class Event(
    @SerializedName("d") val description: String, // Maps "d" to "description"
    @SerializedName("sh") val shortDescription: String, // Maps "sh" to "shortDescription"
    @SerializedName("i") val id: Long, // Maps "i" to "id"
    @SerializedName("si") val sportType: String, // Maps "si" to "sportType"
    @SerializedName("tt") val timestamp: Double // Maps "tt" to "timestamp"
)

