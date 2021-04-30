package com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class MovementPatterns: Parcelable {
    HIP_HINGE { override fun toString() = "Hip Hinge" },
    HORIZONTAL_PULL { override fun toString() = "Horizontal Pull" },
    HORIZONTAL_PUSH { override fun toString() = "Horizontal Push" },
    SQUAT { override fun toString() = "Squat" },
    VERTICAL_PULL { override fun toString() = "Vertical Pull" },
    VERTICAL_PUSH { override fun toString() = "Vertical Push" }
}