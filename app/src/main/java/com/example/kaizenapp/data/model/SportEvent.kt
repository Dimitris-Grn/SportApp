package com.example.kaizenapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SportEvent(
//    @SerialName("i")
//    val sportId: String?,
    @SerialName("d")
    val sportName: String?,
//    @SerialName("e")
//    val listActiveEvents: List<ActiveEvents>
)
