package com.johnfelen.dupsimplified.model.repository

import com.johnfelen.dupsimplified.model.service.WorkoutService
import com.johnfelen.dupsimplified.model.storage.dao.WorkoutDao
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Lift
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Workout
import com.johnfelen.dupsimplified.model.storage.entity.enum.workout.LiftNames
import com.johnfelen.dupsimplified.model.storage.entity.enum.workout.MovementPatterns
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
class WorkoutRepositoryTest {
    @Mock private lateinit var workoutDao: WorkoutDao

    @Mock private lateinit var workoutService: WorkoutService

    @InjectMocks private lateinit var workoutRepository: WorkoutRepository

    @Before fun setUp() = MockitoAnnotations.initMocks(this)

    @Test fun `when getWorkout() is called with a movementPattern that is in the workoutDao, the expected workout should be returned without calling workoutService`() = runBlocking {
        val movementPattern = MovementPatterns.HIP_HINGE
        val expectedWorkout = Workout(movementPattern.toString(), listOf(Lift()))

        doReturn(expectedWorkout).whenever(workoutDao).get(movementPattern.toString())

        val actualWorkout = workoutRepository.getWorkout(movementPattern)
        verify(workoutService, never()).getWorkout(any())
        assertEquals(expectedWorkout, actualWorkout)
    }

    @Test fun `when getWorkout() is called with a movementPattern that is not in the workoutDao and workoutService is successful, the expected workout should be returned and added to the workoutDao`() = runBlocking {
        val movementPattern = MovementPatterns.VERTICAL_PUSH
        val expectedWorkout = Workout(movementPattern.toString(), listOf(Lift()))

        doReturn(null).whenever(workoutDao).get(any())
        doReturn(Response.success(expectedWorkout)).whenever(workoutService).getWorkout(movementPattern)

        val actualWorkout = workoutRepository.getWorkout(movementPattern)
        verify(workoutService, times(1)).getWorkout(movementPattern)
        verify(workoutDao, times(1)).insert(actualWorkout!!)
        assertEquals(expectedWorkout, actualWorkout)
    }

    @Test fun `when getWorkout() is called with a movementPattern that is not in the workoutDao and the workoutService is not successful, an error should be thrown without adding it to the workoutDao`() = runBlocking {
        val movementPattern = MovementPatterns.VERTICAL_PUSH
        val workout = Workout(movementPattern.toString())
        val workoutDaoReturn = null
        val errorResponse = Response.error<String>(400, "".toResponseBody("application/json".toMediaTypeOrNull()))

        doReturn(workoutDaoReturn).whenever(workoutDao).get(any())
        doReturn(errorResponse).whenever(workoutService).getWorkout(movementPattern)

        assertNull(workoutRepository.getWorkout(movementPattern))
        verify(workoutService, times(1)).getWorkout(movementPattern)
        verify(workoutDao, never()).insert(workout)
    }

    @Test fun `when updateOneRepMax() is called with a valid liftName and repsInLastSet and workoutService is successful, a non-null value should be returned`() = runBlocking {
        val liftName = LiftNames.DEADLIFT
        val repsInLastSet = 1
        val expectedNewMax = 100.0

        doReturn(Response.success(expectedNewMax)).whenever(workoutService).updateOneRepMax(liftName, repsInLastSet)

        val actualNewMax = workoutRepository.updateOneRepMax(liftName, repsInLastSet)
        assertEquals(expectedNewMax, actualNewMax)
    }

    @Test fun `when updateOneRepMax() is called with a invalid liftName and repsInLastSet and workoutService is unsuccessful, a null value should be returned`() = runBlocking {
        val liftName = LiftNames.BENCH_PRESS
        val repsInLastSet = 1
        val expectedNewMax = null
        val errorResponse = Response.error<String>(400, "".toResponseBody("application/json".toMediaTypeOrNull()))

        doReturn(errorResponse).whenever(workoutService).updateOneRepMax(liftName, repsInLastSet)

        val actualNewMax = workoutRepository.updateOneRepMax(liftName, repsInLastSet)
        assertEquals(expectedNewMax, actualNewMax)
    }
}