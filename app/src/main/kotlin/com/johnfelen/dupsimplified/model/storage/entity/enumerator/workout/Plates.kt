package com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout

enum class Plates {
    RED { override fun toString() = "Red" },
    BLUE { override fun toString() = "Blue" },
    YELLOW { override fun toString() = "Yellow" },
    GREEN { override fun toString() = "Greene" },
    WHITE { override fun toString() = "White" },
    BLACK { override fun toString() = "Black" },
    GREEN_CHANGE { override fun toString() = "Green Change" },
    WHITE_FRACTIONAL { override fun toString() = "White Fractional" },
    BLUE_FRACTIONAL { override fun toString() = "Blue Fractional" },
    GREEN_FRACTIONAL { override fun toString() = "Green Fractional" },
}