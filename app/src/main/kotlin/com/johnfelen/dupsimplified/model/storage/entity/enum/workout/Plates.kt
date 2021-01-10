package com.johnfelen.dupsimplified.model.storage.entity.enum.workout

enum class Plates {
    BLUE_BUMPER { override fun toString() = "Blue Bumper" },
    GREEN_BUMPER { override fun toString() = "Green Bumper" },
    WHITE_CHANGE { override fun toString() = "White Change" },
    BLUE_CHANGE { override fun toString() = "Blue Change" },
    GREEN_CHANGE { override fun toString() = "Green Change" },
    WHITE_FRACTIONAL { override fun toString() = "White Fractional" },
    BLUE_FRACTIONAL { override fun toString() = "Blue Fractional" },
    GREEN_FRACTIONAL { override fun toString() = "Green Fractional" }
}