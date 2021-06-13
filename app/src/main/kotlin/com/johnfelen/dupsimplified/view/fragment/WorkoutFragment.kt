package com.johnfelen.dupsimplified.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.johnfelen.dupsimplified.R
import com.johnfelen.dupsimplified.view.adapter.LiftListAdapter
import com.johnfelen.dupsimplified.view.adapter.fillView
import com.johnfelen.dupsimplified.viewmodel.Resource
import com.johnfelen.dupsimplified.viewmodel.WorkoutViewModel
import com.johnfelen.dupsimplified.viewmodel.sharedViewModel
import kotlinx.android.synthetic.main.fragment_workout.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class WorkoutFragment: Fragment(R.layout.fragment_workout), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val workoutViewModel: WorkoutViewModel by sharedViewModel()
    private val workoutFragmentArgs: WorkoutFragmentArgs by navArgs()
    private val liftListAdapter: LiftListAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutViewModel.getWorkout(workoutFragmentArgs.selectedMovementPattern)
        workoutViewModel.selectedWorkout.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> recycler_view_lifts.fillView(liftListAdapter, it.data.lifts, 1)
                is Resource.Error -> findNavController().popBackStack()
            }
        }

        workoutViewModel.newOneRepMax.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> Toast.makeText(context, "New 1RM is ${it.data}", Toast.LENGTH_LONG).show()
                is Resource.Error -> Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }

        }
    }
}