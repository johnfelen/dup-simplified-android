package com.johnfelen.dupsimplified.model.storage.entity.enumerator.workout

enum class LiftNames {
    BENCH_PRESS,
    KADILLAC_SLINGSHOT_REVERSE_GRIP_BENCH_PRESS,
    FLOOR_PRESS,
    KADILLAC_CLOSE_GRIP_BENCH_PRESS,
    RING_PUSH_UP,

    PENDLAY_ROW,
    HIGH_HANDLE_TRAP_HD_ROW,
    TRAP_HD_SEAL_ROW,
    CABLE_SEATED_ROW,
    RING_INVERTED_ROW,

    DEADLIFT,
    HIGH_HANDLE_TRAP_HD_DEADLIFT,
    SUMO_DEADLIFT,
    HIP_HINGE_TRANSFORMER_GOOD_MORNING{
        override fun toString() = super.toString() + "***"
    },
    RING_GLIDING_HAMSTRING_CURL,

    PRESS,
    PUSH_PRESS,
    Z_PRESS,
    KADILLAC_PRESS,
    RING_DIPSm,

    UPRIGHT_ROW,
    HIGH_HANDLE_TRAP_HD_HIGH_PULL,
    CLOSE_GRIP_KNEELING_LAT_PULLDOWN,
    STRAIGHT_ARM_PULLDOWN,
    RING_CHIN_UP,

    LOW_BAR_SQUAT,
    LOW_BAR_STAND_STRONG_TRANSFORMER_SQUAT{
        override fun toString() = super.toString() + "***"
    },
    SSB_TRANSFORMER_BOX_SQUAT{
        override fun toString() = super.toString() + "***"
    },
    TRANSFORMER_FRONT_SQUAT{
        override fun toString() = super.toString() + "***"
    },
    RING_SQUAT
}