package com.johnfelen.dupsimplified.model.storage.entity.data.workout

import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.LiftNames

data class Lift(
    val liftName: LiftNames = LiftNames.DEADLIFT,
    val warmUpSets: List<Set> = emptyList(),
    val workSet: Set = Set(),
    val workSetCount: Int = 0
)