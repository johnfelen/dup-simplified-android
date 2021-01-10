package com.johnfelen.dupsimplified.model.storage.entity.data.workout

data class Set(
    val reps: Int = 0,
    val plateCounts: List<PlateCount> = emptyList()
)