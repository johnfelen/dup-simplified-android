package com.johnfelen.dupsimplified.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.johnfelen.dupsimplified.databinding.ItemLiftBinding
import com.johnfelen.dupsimplified.model.storage.entity.data.workout.Lift
import com.johnfelen.dupsimplified.view.fragment.WorkoutFragmentDirections

class LiftListAdapter: ListAdapter<Lift, LiftListAdapter.ViewHolder>(LiftListAdapterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemLiftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(private val binding: ItemLiftBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Lift) = with(binding) {
            lift = item

            root.setOnClickListener {
                item.warmUpSets.plus(List(item.workSetCount) { item.workSet }).toTypedArray().let { sets ->
                    it.findNavController().navigate(WorkoutFragmentDirections.actionWorkoutFragmentToSetFragment(sets, item.liftName))
                }
            }

            executePendingBindings()
        }
    }

    private class LiftListAdapterDiffCallback: DiffUtil.ItemCallback<Lift>() {
        override fun areItemsTheSame(oldItem: Lift, newItem: Lift) = oldItem.liftName == newItem.liftName

        override fun areContentsTheSame(oldItem: Lift, newItem: Lift) = oldItem == newItem
    }
}