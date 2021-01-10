package com.johnfelen.dupsimplified.model.storage.entity.data.workout

import com.johnfelen.dupsimplified.model.storage.entity.enum.workout.Plates

data class PlateCount(
    val plate: Plates = Plates.BLUE_BUMPER,
    val count: Int = 0
)