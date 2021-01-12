package com.johnfelen.dupsimplified.model.storage.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.johnfelen.dupsimplified.model.storage.database.WorkoutDatabase
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Workout
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.MovementPatterns
import junit.framework.Assert.assertNull
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkoutDaoTest {
    private lateinit var workoutDatabase: WorkoutDatabase

    private lateinit var workoutDao: WorkoutDao

    @Before fun setUp() {
        workoutDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, WorkoutDatabase::class.java)
            .build().also { workoutDao = it.workoutDao() }
    }

    @After fun tearDown() {
        workoutDatabase.close()
    }

    @Test fun insert() = workoutDao.run {
        val workoutToReplace = Workout(MovementPatterns.HORIZONTAL_PUSH.name)

        insert(workoutToReplace)
        insert(workoutToReplace)
        assertEquals(workoutToReplace, get(workoutToReplace.primaryMovementPattern))
    }

    @Test fun get() = workoutDao.run {
        val expectedWorkout = Workout(MovementPatterns.HIP_HINGE.name)

        assertNull(get(expectedWorkout.primaryMovementPattern))
        insert(expectedWorkout)
        assertNotNull(get(expectedWorkout.primaryMovementPattern))
    }

    @Test fun delete() = workoutDao.run {
        val workoutToDelete = Workout(MovementPatterns.SQUAT.name)

        insert(workoutToDelete)
        assertNotNull(get(workoutToDelete.primaryMovementPattern))
        delete(MovementPatterns.SQUAT.name)
        assertNull(get(workoutToDelete.primaryMovementPattern))
    }
}