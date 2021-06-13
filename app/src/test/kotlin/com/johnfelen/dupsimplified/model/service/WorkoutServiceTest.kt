package com.johnfelen.dupsimplified.model.service

import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Lift
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.PlateCount
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Set
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Workout
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.LiftNames
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.MovementPatterns
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.Plates
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import util.TestServerRule
import kotlin.random.Random
import kotlin.random.nextInt

class WorkoutServiceTest {
    @get:Rule val testServerRule = TestServerRule()

    private lateinit var workoutService: WorkoutService

    @Before fun setUp() {
        workoutService = testServerRule.instantiateClient()
    }

    @Test fun `when getWorkout() is called with a valid MovementPattern a successful Workout should be returned`() = testServerRule.runServerBlocking {
        val validPrimaryMovementPattern = MovementPatterns.HIP_HINGE
        val successfulWorkout = Workout(validPrimaryMovementPattern.toString(),
            listOf(
                Lift(LiftNames.DEADLIFT,
                    listOf(
                        Set(3, listOf(PlateCount(Plates.RED, 2), PlateCount(Plates.BLUE, 1))),
                        Set(3, listOf(PlateCount(Plates.RED, 2), PlateCount(Plates.BLUE, 1)))
                    )
                ),
                Lift(LiftNames.BENCH_PRESS,
                    listOf(
                        Set(5, listOf(PlateCount(Plates.RED, 1), PlateCount(Plates.YELLOW, 2))),
                        Set(3, listOf(PlateCount(Plates.RED, 2), PlateCount(Plates.GREEN, 1)))
                    ),
                    Set(6, listOf(PlateCount(Plates.RED, 3))), 6
                )
            )
        )

        it.enqueue(MockResponse().setBody(ClassLoader.getSystemResource("success/getWorkout().json")!!.readText()))

        workoutService.getWorkout(validPrimaryMovementPattern.name).run {
            assertEquals(true, isSuccessful)
            assertEquals(200, code())
            assertEquals(successfulWorkout, body())
        }
    }

    @Test fun `when getWorkout() is called and an error happens null should be returned`() = testServerRule.runServerBlocking {
        val invalidWorkoutDay = MovementPatterns.SQUAT.name
        val errorResponseCode = Random(0).nextInt(400..599)

        it.enqueue(MockResponse().setResponseCode(errorResponseCode))

        workoutService.getWorkout(invalidWorkoutDay).run {
            assertEquals(false, isSuccessful)
            assertEquals(errorResponseCode, code())
            assertEquals(null, body())
        }
    }

    @Test fun `when updateOneRepMax() is called with a valid liftName and repsInLastSet a successful Double should be returned`() = testServerRule.runServerBlocking {
        val validLiftName = LiftNames.PENDLAY_ROW.name
        val repsInLastSet = 8
        val newTheoreticalOneRepMax = 100.5

        it.enqueue(MockResponse().setBody(newTheoreticalOneRepMax.toString()))

        workoutService.updateOneRepMax(validLiftName, repsInLastSet).run {
            assertEquals(true, isSuccessful)
            assertEquals(200, code())
            assertEquals(newTheoreticalOneRepMax, body())
        }
    }

    @Test fun `when updateOneRepMax() is called and an error happens null should be returned`() = testServerRule.runServerBlocking {
        val invalidLiftName = LiftNames.RING_GLIDING_HAMSTRING_CURL.name
        val repsInLastSet = 8
        val errorResponseCode = Random(0).nextInt(400..599)

        it.enqueue(MockResponse().setResponseCode(errorResponseCode).setBody("null"))

        workoutService.updateOneRepMax(invalidLiftName, repsInLastSet).run {
            assertEquals(false, isSuccessful)
            assertEquals(errorResponseCode, code())
            assertEquals(null, body())
        }
    }
}