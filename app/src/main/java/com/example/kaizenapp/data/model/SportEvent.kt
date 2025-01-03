package com.example.kaizenapp.data.model


data class SportData(
    val d: String, // Sport name
    val e: List<SportEvent>, // List of sport events
    val i: String? // Sport identifier
)

data class SportEvent(
    val d: String, // Event description
    val i: Long, // Event ID
    val sh: String, // Short event description
    val si: String, // Sport identifier (within the category)
    val tt: Double // Timestamp
)

data class SportEventFinal(val sportName:String?, val listSportEvent: List<SportEvent>?)
