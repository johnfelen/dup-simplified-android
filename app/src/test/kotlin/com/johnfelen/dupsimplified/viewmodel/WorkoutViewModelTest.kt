package com.johnfelen.dupsimplified.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.johnfelen.dupsimplified.model.repository.WorkoutRepository
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Lift
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Workout
import com.johnfelen.dupsimplified.model.storage.entity.enum.workout.LiftNames
import com.johnfelen.dupsimplified.model.storage.entity.enum.workout.MovementPatterns
import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import util.TestCoroutineRule

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class)
class WorkoutViewModelTest {
    @get:Rule val testCoroutineRule = TestCoroutineRule()

    @Rule @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var workoutRepository: WorkoutRepository

    @Spy @InjectMocks
    private lateinit var workoutViewModel: WorkoutViewModel

    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test fun `when startWorkout() is called with valid movementPattern, Loading and valid Success should be posted`() = testCoroutineRule.runTest {
        val movementPattern = MovementPatterns.SQUAT
        val expectedWorkout = Workout(movementPattern.toString())

        doReturn(expectedWorkout).whenever(workoutRepository).getWorkout(movementPattern)

        workoutViewModel.getWorkout(movementPattern)
        workoutViewModel.selectedWorkout.test().also {
            it.valueHistory().first() is Resource.Loading
        }.assertValue {
            it is Resource.Success && it.data == expectedWorkout
        }
    }

    @Test fun `when updateOneRepMax() is called but it is not the lasoutObservert lift, Loading and valid Success should be posted and completeWorkout() should not be called`() = testCoroutineRule.runTest {
        val movementPattern = MovementPatterns.SQUAT
        val liftName = LiftNames.Z_PRESS
        val repsInLastSet = 7
        val expectedNewOneRepMax = 100.5

        doReturn(Workout()).whenever(workoutRepository).getWorkout(movementPattern)
        doReturn(expectedNewOneRepMax).whenever(workoutRepository).updateOneRepMax(liftName, repsInLastSet)

        workoutViewModel.getWorkout(movementPattern)
        workoutViewModel.updateOneRepMax(liftName, repsInLastSet)
        workoutViewModel.newOneRepMax.test().also {
            it.valueHistory().first() is Resource.Loading
        }.assertValue {
            it is Resource.Success && it.data == expectedNewOneRepMax
        }
        verify(workoutRepository, never()).completeWorkout(movementPattern)
    }

    @Test fun `when updateOneRepMax() is called and it is the last lift, Loading and valid Success should be posted and completeWorkout() should be called`() = testCoroutineRule.runTest {
        val movementPattern = MovementPatterns.SQUAT
        val liftName = LiftNames.Z_PRESS
        val repsInLastSet = 7
        val expectedNewOneRepMax = 100.5

        doReturn(Workout(movementPattern.toString(), listOf(Lift(liftName)))).whenever(workoutRepository).getWorkout(movementPattern)
        doReturn(expectedNewOneRepMax).whenever(workoutRepository).updateOneRepMax(liftName, repsInLastSet)

        workoutViewModel.getWorkout(movementPattern)
        workoutViewModel.updateOneRepMax(liftName, repsInLastSet)
        workoutViewModel.newOneRepMax.test().also {
            it.valueHistory().first() is Resource.Loading
        }.assertValue {
            it is Resource.Success && it.data == expectedNewOneRepMax
        }
        verify(workoutRepository).completeWorkout(movementPattern)
    }
}