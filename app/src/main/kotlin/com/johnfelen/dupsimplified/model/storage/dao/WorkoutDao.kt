package com.johnfelen.dupsimplified.model.storage.dao

import androidx.room.*
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Workout
import com.johnfelen.dupsimplified.model.storage.entity.enum.workout.MovementPatterns

@Dao interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(workout: Workout)

    @Query("SELECT * FROM Workout WHERE primaryMovementPattern = :primaryMovementPattern") fun get(primaryMovementPattern: String): Workout?

    @Query("DELETE FROM Workout WHERE primaryMovementPattern = :primaryMovementPattern") fun delete(primaryMovementPattern: String)
}