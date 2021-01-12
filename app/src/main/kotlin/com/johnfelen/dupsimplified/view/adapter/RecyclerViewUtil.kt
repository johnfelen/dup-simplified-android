package com.johnfelen.dupsimplified.view.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

fun <T, VH: RecyclerView.ViewHolder> RecyclerView.fillView(listAdapter: ListAdapter<T, VH>, data: List<T>, spanCount: Int) {
    setHasFixedSize(true)
    layoutManager = object: GridLayoutManager(context, spanCount.coerceAtLeast(1)) {
        override fun canScrollVertically() = false
        override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
            lp?.height = height / data.count().div(spanCount)
            return true
        }
    }
    adapter = listAdapter.apply { submitList(data) }
}