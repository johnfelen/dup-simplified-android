package com.johnfelen.dupsimplified.model.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.johnfelen.dupsimplified.model.storage.dao.WorkoutDao
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Lift
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Workout

@TypeConverters(WorkoutTypeConverters::class)
@Database(entities = [Workout::class], version = 1, exportSchema = false)
abstract class WorkoutDatabase: RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}

class WorkoutTypeConverters {
    @TypeConverter fun toLifts(liftsAsString: String) = Gson().fromJson(liftsAsString, Array<Lift>::class.java).toList()

    @TypeConverter fun fromLifts(lifts: List<Lift>): String = Gson().toJson(lifts)
}