package com.johnfelen.dupsimplified.model.service

import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Workout
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WorkoutService {
    companion object {
        const val prefix = "workout"
    }

    @GET("/$prefix/primaryMovementPattern/{primaryMovementPattern}")
    suspend fun getWorkout(@Path("primaryMovementPattern") primaryMovementPattern: String): Response<Workout>

    @POST("/$prefix/liftName/{liftName}/repsInLastSet/{repsInLastSet}")
    suspend fun updateOneRepMax(@Path("liftName") liftName: String, @Path("repsInLastSet") repsInLastSet: Int): Response<Double>
}