package com.johnfelen.dupsimplified.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.johnfelen.dupsimplified.R
import com.johnfelen.dupsimplified.databinding.FragmentSetBinding
import com.johnfelen.dupsimplified.view.adapter.PlateListAdapter
import com.johnfelen.dupsimplified.view.adapter.fillView
import kotlinx.android.synthetic.main.fragment_set.*
import kotlinx.android.synthetic.main.fragment_workout.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SetFragment: Fragment(R.layout.fragment_set), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val fragmentSetBinding: FragmentSetBinding by viewBinding(FragmentSetBinding::bind)
    private val setFragmentArgs: SetFragmentArgs by navArgs()
    private val plateListAdapter: PlateListAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val (sets, currentSetIndex) = setFragmentArgs

        fragmentSetBinding.setProgress = "${currentSetIndex + 1} / ${sets.count()}"
        if(currentSetIndex == sets.lastIndex) fragmentSetBinding.buttonNextSet.text = getString(R.string.complete_lift)

        button_next_set.setOnClickListener {
            findNavController().run {
                if(currentSetIndex == sets.lastIndex) popBackStack()
                else navigate(SetFragmentDirections.actionSetFragmentSelf(sets, currentSetIndex + 1))
            }
        }

        sets[currentSetIndex].plateCounts.flatMap { (plate, count) ->
            List(count) { plate }
        }.let { recycler_view_plates.fillView(plateListAdapter, it, it.count()) }
    }
}