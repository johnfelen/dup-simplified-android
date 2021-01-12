package com.johnfelen.dupsimplified.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.johnfelen.dupsimplified.databinding.ItemPlateBinding
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.Plates

class PlateListAdapter: ListAdapter<Plates, PlateListAdapter.ViewHolder>(PlateListAdapterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemPlateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(private val binding: ItemPlateBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plates) = with(binding) {
            plate = item

            executePendingBindings()
        }
    }

    private class PlateListAdapterDiffCallback: DiffUtil.ItemCallback<Plates>() {
        override fun areItemsTheSame(oldItem: Plates, newItem: Plates) = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Plates, newItem: Plates) = oldItem == newItem
    }
}