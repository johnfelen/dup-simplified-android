package com.johnfelen.dupsimplified.model.storage.entity.data.workout

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Set(
    val reps: Int = 0,
    val plateCounts: List<PlateCount> = emptyList()
): Parcelable