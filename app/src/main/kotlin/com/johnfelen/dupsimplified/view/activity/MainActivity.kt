package com.johnfelen.dupsimplified.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.johnfelen.dupsimplified.R
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.MovementPatterns
import com.johnfelen.dupsimplified.viewmodel.Resource
import com.johnfelen.dupsimplified.viewmodel.WorkoutViewModel
import com.johnfelen.dupsimplified.viewmodel.viewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class MainActivity: AppCompatActivity(R.layout.activity_main), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val workoutViewModel: WorkoutViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        workoutViewModel.selectedWorkout.observe(this) {
            main_progress_bar.visibility = View.GONE
            when(it) {
                is Resource.Loading -> main_progress_bar.visibility = View.VISIBLE
                is Resource.Error -> Toast.makeText(this, "Error Loading Workout!", Toast.LENGTH_LONG).show()
            }
        }
    }
}