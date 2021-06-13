package com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class MovementPatterns: Parcelable {
    HORIZONTAL_PUSH { override fun toString() = "Horizontal Push" },
    HORIZONTAL_PULL { override fun toString() = "Horizontal Pull" },
    HIP_HINGE { override fun toString() = "Hip Hinge" },
    VERTICAL_PUSH { override fun toString() = "Vertical Push" },
    VERTICAL_PULL { override fun toString() = "Vertical Pull" },
    SQUAT { override fun toString() = "Squat" }
}