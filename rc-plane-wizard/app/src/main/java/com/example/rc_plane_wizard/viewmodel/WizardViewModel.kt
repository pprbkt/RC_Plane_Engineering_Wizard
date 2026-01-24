package com.example.rc_plane_wizard.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow

class WizardViewModel : ViewModel() {
    private val _state = MutableStateFlow(WizardState())
    val state: StateFlow<WizardState> = _state.asStateFlow()

    init {
        recalculate()
    }

    fun updateMission(
        mode: CompetitionMode,
        wingConfig: WingConfiguration,
        maxWingspan: String,
        maxProp: String
    ) {
        _state.update {
            it.copy(
                competitionMode = mode,
                wingConfiguration = wingConfig,
                maxWingspanMm = maxWingspan,
                maxPropInch = maxProp
            )
        }
        recalculate()
    }

    fun updateWeight(empty: String, payload: String) {
        _state.update { it.copy(emptyWeightG = empty, payloadWeightG = payload) }
        recalculate()
    }

    fun updatePropulsion(kv: String, voltage: String, pitch: String, diameter: String) {
        _state.update {
            it.copy(
                motorKv = kv,
                batteryVoltage = voltage,
                propPitchInch = pitch,
                propDiameterInch = diameter
            )
        }
        recalculate()
    }
    
    // Auto-select logic
    fun onCompetitionModeSelected(mode: CompetitionMode) {
        val recommendedWing = when (mode) {
            CompetitionMode.PAYLOAD, CompetitionMode.TRAINER -> WingConfiguration.HIGH_WING
            CompetitionMode.AEROBATICS -> WingConfiguration.MID_WING
            CompetitionMode.RACING -> WingConfiguration.LOW_WING
        }
        _state.update {
            it.copy(
                competitionMode = mode,
                wingConfiguration = recommendedWing
            )
        }
        recalculate()
    }

    private fun recalculate() {
        _state.update { currentState ->
            // Parse inputs safely
            val emptyW = currentState.emptyWeightG.toDoubleOrNull() ?: 0.0
            val payloadW = currentState.payloadWeightG.toDoubleOrNull() ?: 0.0
            val maxSpan = currentState.maxWingspanMm.toDoubleOrNull() ?: 1000.0 // Avoid div by zero
            val kv = currentState.motorKv.toDoubleOrNull() ?: 0.0
            val volts = currentState.batteryVoltage.toDoubleOrNull() ?: 0.0
            val pitch = currentState.propPitchInch.toDoubleOrNull() ?: 0.0
            val diam = currentState.propDiameterInch.toDoubleOrNull() ?: 0.0

            // Step 2: Weight
            val totalWeight = emptyW + payloadW
            val weightForce = (totalWeight / 1000.0) * 9.81

            // Step 3: Propulsion
            val rpm = kv * volts * 0.85
            val pitchSpeed = (rpm * pitch * 0.0254) / 60.0
            val cruiseSpeed = pitchSpeed * 0.75
            val staticThrust = 3.0e-10 * rpm.pow(2) * diam.pow(3) * pitch.pow(0.5) * 28.35
            
            val isUnderpowered = staticThrust < totalWeight && currentState.competitionMode != CompetitionMode.TRAINER

            // Step 4: Wing Sizing
            // Avoid division by zero
            val vCruiseSafe = if (cruiseSpeed > 0) cruiseSpeed else 1.0
            val targetCl = currentState.competitionMode.targetCl
            
            val requiredAreaM2 = (2 * weightForce) / (1.225 * vCruiseSafe.pow(2) * targetCl)
            val maxWingspanM = maxSpan / 1000.0
            val chordMm = if (maxWingspanM > 0) (requiredAreaM2 / maxWingspanM) * 1000.0 else 0.0
            val wingLoading = if (requiredAreaM2 > 0) totalWeight / (requiredAreaM2 * 100.0) else 0.0

            // Step 5: Tail Sizing
            val momentArmMm = 2.5 * chordMm
            val tailVolH = currentState.competitionMode.tailVolH
            
            val chordM = chordMm / 1000.0
            val momentArmM = momentArmMm / 1000.0
            
            val hStabArea = if (momentArmM > 0) ((tailVolH * requiredAreaM2 * chordM) / momentArmM) * 100.0 else 0.0
            val vStabArea = if (momentArmM > 0) ((0.04 * requiredAreaM2 * (maxWingspanM)) / momentArmM) * 100.0 else 0.0

            // Step 6: Layout
            val leadingEdgePos = chordMm * 1.0
            val cgPos = chordMm * 0.30

            currentState.copy(
                totalWeightG = totalWeight,
                weightForceNewtons = weightForce,
                
                rpm = rpm,
                pitchSpeedMs = pitchSpeed,
                cruiseSpeedMs = cruiseSpeed,
                staticThrustG = staticThrust,
                isUnderpowered = isUnderpowered,
                
                requiredWingAreaM2 = requiredAreaM2,
                rootChordMm = chordMm,
                wingLoading = wingLoading,
                
                tailMomentArmMm = momentArmMm,
                hStabAreaCm2 = hStabArea,
                vStabAreaCm2 = vStabArea,
                
                wingLeadingEdgePosMm = leadingEdgePos,
                cgPosMm = cgPos
            )
        }
    }
}
