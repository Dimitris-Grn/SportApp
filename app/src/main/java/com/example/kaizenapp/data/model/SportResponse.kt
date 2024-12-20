package com.example.kaizenapp.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
@Serializable
data class SportResponse(
    val sports: List<SportEvent>)

