package com.johnfelen.dupsimplified.model.storage.entity.data.workout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout(
    @PrimaryKey val primaryMovementPattern: String = "",
    val lifts: List<Lift> = emptyList()
)