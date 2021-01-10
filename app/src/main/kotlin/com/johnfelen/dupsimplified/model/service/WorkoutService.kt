package com.johnfelen.dupsimplified.model.service

import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Workout
import com.johnfelen.dupsimplified.model.storage.entity.enum.workout.LiftNames
import com.johnfelen.dupsimplified.model.storage.entity.enum.workout.MovementPatterns
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WorkoutService {
    @GET("/primaryMovementPattern/{primaryMovementPattern}")
    suspend fun getWorkout(@Path("primaryMovementPattern") primaryMovementPattern: MovementPatterns): Response<Workout>

    @POST("/liftName/{liftName}/repsInLastSet/{repsInLastSet}")
    suspend fun updateOneRepMax(@Path("liftName") liftName: LiftNames, @Path("repsInLastSet") repsInLastSet: Int): Response<Double>
}