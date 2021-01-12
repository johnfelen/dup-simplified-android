package com.johnfelen.dupsimplified.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.johnfelen.dupsimplified.R
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.MovementPatterns
import com.johnfelen.dupsimplified.view.adapter.MovementPatternListAdapter
import com.johnfelen.dupsimplified.view.adapter.fillView
import kotlinx.android.synthetic.main.fragment_training_day.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class TrainingDayFragment: Fragment(R.layout.fragment_training_day), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val movementPatternListAdapter: MovementPatternListAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view_movement_patterns.fillView(movementPatternListAdapter, MovementPatterns.values().toList(), 2)
    }
}