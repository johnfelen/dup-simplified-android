package com.johnfelen.dupsimplified.model.storage.entity.data.workout

import android.os.Parcelable
import com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout.Plates
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlateCount(
    val plate: Plates = Plates.RED,
    val count: Int = 0
): Parcelable