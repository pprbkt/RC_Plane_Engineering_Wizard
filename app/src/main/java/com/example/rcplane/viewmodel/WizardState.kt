package com.example.rcplane.viewmodel

enum class CompetitionMode(val displayName: String, val targetCl: Double, val tailVolH: Double) {
    PAYLOAD("Payload", 0.8, 0.6),
    RACING("Racing", 0.2, 0.35),
    TRAINER("Trainer", 0.5, 0.45),
    AEROBATICS("Aerobatics", 0.3, 0.50)
}

enum class WingConfiguration(val displayName: String) {
    HIGH_WING("High Wing"),
    MID_WING("Mid Wing"),
    LOW_WING("Low Wing")
}

data class WizardState(
    // Step 1: Mission
    val competitionMode: CompetitionMode = CompetitionMode.TRAINER,
    val wingConfiguration: WingConfiguration = WingConfiguration.HIGH_WING,
    val maxWingspanMm: String = "1200", // Using String for TextField binding
    val maxPropInch: String = "10",

    // Step 2: Weight
    val emptyWeightG: String = "800",
    val payloadWeightG: String = "0",

    // Step 3: Propulsion
    val motorKv: String = "920",
    val batteryVoltage: String = "11.1",
    val propPitchInch: String = "6",
    val propDiameterInch: String = "10",

    // Calculations
    val totalWeightG: Double = 0.0,
    val weightForceNewtons: Double = 0.0,
    
    val rpm: Double = 0.0,
    val pitchSpeedMs: Double = 0.0,
    val cruiseSpeedMs: Double = 0.0,
    val staticThrustG: Double = 0.0,
    val isUnderpowered: Boolean = false,

    val requiredWingAreaM2: Double = 0.0,
    val rootChordMm: Double = 0.0,
    val wingLoading: Double = 0.0,

    val tailMomentArmMm: Double = 0.0,
    val hStabAreaCm2: Double = 0.0,
    val vStabAreaCm2: Double = 0.0,

    // Layout
    val wingLeadingEdgePosMm: Double = 0.0,
    val cgPosMm: Double = 0.0
)
