package com.johnfelen.dupsimplified.model.repository

import com.johnfelen.dupsimplified.model.service.WorkoutService
import com.johnfelen.dupsimplified.model.storage.dao.WorkoutDao
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.LiftNames
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.MovementPatterns

class WorkoutRepository(private val workoutDao: WorkoutDao, private val workoutService: WorkoutService) {
    suspend fun getWorkout(movementPattern: MovementPatterns) = workoutDao.get(movementPattern.toString()) ?: workoutService.runCatching {
        getWorkout(movementPattern.name).takeIf { it.isSuccessful }?.body()!!.also(workoutDao::insert)
    }.getOrNull()

    suspend fun updateOneRepMax(liftName: LiftNames, repsInLastSet: Int) = workoutService.runCatching {
        updateOneRepMax(liftName.name, repsInLastSet).takeIf { it.isSuccessful }?.body()!!
    }.getOrNull()

    fun completeWorkout(movementPattern: MovementPatterns) = workoutDao.delete(movementPattern.toString())
}