package com.johnfelen.dupsimplified.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.johnfelen.dupsimplified.databinding.ItemMovementPatternBinding
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.MovementPatterns
import com.johnfelen.dupsimplified.view.fragment.TrainingDayFragmentDirections

class MovementPatternListAdapter: ListAdapter<MovementPatterns, MovementPatternListAdapter.ViewHolder>(MovementPatternListAdapterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemMovementPatternBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(private val binding: ItemMovementPatternBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovementPatterns) = with(binding) {
            movementPattern = item

            root.setOnClickListener {
                it.findNavController().navigate(TrainingDayFragmentDirections.actionTrainingDayFragmentToWorkoutFragment(item))
            }

            executePendingBindings()
        }
    }

    private class MovementPatternListAdapterDiffCallback: DiffUtil.ItemCallback<MovementPatterns>() {
        override fun areItemsTheSame(oldItem: MovementPatterns, newItem: MovementPatterns) = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: MovementPatterns, newItem: MovementPatterns) = oldItem == newItem
    }
}