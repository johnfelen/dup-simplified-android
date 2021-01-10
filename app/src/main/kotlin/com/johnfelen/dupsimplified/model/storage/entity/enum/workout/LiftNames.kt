package com.johnfelen.dupsimplified.model.storage.entity.enum.workout

enum class LiftNames {
    DEADLIFT { override fun toString() = "Deadlift" },
    BLOCK_PULL { override fun toString() = "Block Pull" },
    PAUSE_DEADLIFT { override fun toString() = "Pause Deadlift" },
    GOOD_MORNING { override fun toString() = "Good Morning" },
    UNILATERAL_GLIDING_HAMSTRING_CURL { override fun toString() = "Unilaterl Gliding Hamstring Curl" },

    BENCH_PRESS { override fun toString() = "Bench Press" },
    SLINGSHOT_BENCH_PRESS { override fun toString() = "Slingshot Bench Press" },
    FLOOR_PRESS { override fun toString() = "Floor Press" },
    CLOSE_GRIP_BENCH_PRESS { override fun toString() = "Close Grip Bench Press" },
    REVERSE_GRIP_DUMBBELL_BENCH_PRESS { override fun toString() = "Reverse Grip Dumbbell Bench Press" },

    WEIGHTED_CHIN_UP { override fun toString() = "Weighted Chin-Up" },
    JUMPING_CHIN_UP { override fun toString() = "Jumping Chin-Up" },
    WEIGHTED_PULL_UP { override fun toString() = "Weighted Pull-Up" },
    LAT_PULLDOWN { override fun toString() = "Lat Pulldown" },
    UNILATERAL_PULLDOWN { override fun toString() = "Unilateral Pulldown" },

    LOW_BAR_SQUAT { override fun toString() = "Low Bar Squat" },
    STAND_STRONG_SQUAT { override fun toString() = "Stand Strong Squat" },
    BOX_SQUAT { override fun toString() = "Box Squat" },
    BELT_SQUAT { override fun toString() = "Belt Squat" },
    DEFICIT_BULGARIAN_SPLIT_SQUAT { override fun toString() = "Deficit Bulgarian Split Squat" },

    PRESS { override fun toString() = "Press" },
    PUSH_PRESS { override fun toString() = "Push Press" },
    Z_PRESS { override fun toString() = "Z Press" },
    KNEELING_LANDMINE_PRESS { override fun toString() = "Kneeling Landmine Press" },
    DUMBBELL_PRESS { override fun toString() = "Dumbbell Press" },

    PENDLAY_ROW { override fun toString() = "Pendlay Row" },
    BLOCK_PENDLAY_ROW { override fun toString() = "Block Pendlay Row" },
    PAUSE_PENDLAY_ROW { override fun toString() = "Pause Pendlay Row" },
    STRAIGHT_ARM_PULLDOWN { override fun toString() = "Straight-Arm Pulldown" },
    DUMBBELL_SEAL_ROW { override fun toString() = "Dumbbell Seal Row" }
}