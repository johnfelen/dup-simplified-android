package com.johnfelen.dupsimplified.model.storage.entity.enum.workout

enum class MovementPatterns {
    HIP_HINGE { override fun toString() = "Hip Hinge" },
    HORIZONTAL_PUSH { override fun toString() = "Horizontal Push" },
    VERTICAL_PULL { override fun toString() = "Vertical Pull" },
    SQUAT { override fun toString() = "Squat" },
    VERTICAL_PUSH { override fun toString() = "Vertical Push" },
    HORIZONTAL_PULL { override fun toString() = "Horizontal Pull" }
}