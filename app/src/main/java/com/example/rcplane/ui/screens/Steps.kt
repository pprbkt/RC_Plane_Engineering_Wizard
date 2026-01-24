package com.example.rcplane.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rcplane.ui.components.NeoCard
import com.example.rcplane.ui.components.NeoDropdown
import com.example.rcplane.ui.components.NeoTextField
import com.example.rcplane.ui.theme.NeoCyan
import com.example.rcplane.ui.theme.NeoGreen
import com.example.rcplane.ui.theme.NeoPink
import com.example.rcplane.ui.theme.NeoYellow
import com.example.rcplane.viewmodel.CompetitionMode
import com.example.rcplane.viewmodel.WingConfiguration
import com.example.rcplane.viewmodel.WizardViewModel

@Composable
fun StepContainer(
    title: String,
    stepNumber: Int,
    color: Color = NeoYellow,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        NeoCard(backgroundColor = color) {
            Column {
                Text(
                    text = "STEP 0$stepNumber",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        content()
    }
}

@Composable
fun MissionStep(viewModel: WizardViewModel) {
    val state by viewModel.state.collectAsState()

    StepContainer(title = "Mission", stepNumber = 1) {
        NeoCard {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                NeoDropdown(
                    options = CompetitionMode.values().map { it.displayName },
                    selectedOption = state.competitionMode.displayName,
                    onOptionSelected = { name ->
                        val mode = CompetitionMode.values().find { it.displayName == name } ?: CompetitionMode.TRAINER
                        viewModel.onCompetitionModeSelected(mode)
                    },
                    label = "MODE"
                )

                NeoDropdown(
                    options = WingConfiguration.values().map { it.displayName },
                    selectedOption = state.wingConfiguration.displayName,
                    onOptionSelected = { name ->
                        val config = WingConfiguration.values().find { it.displayName == name } ?: WingConfiguration.HIGH_WING
                        viewModel.updateMission(state.competitionMode, config, state.maxWingspanMm, state.maxPropInch)
                    },
                    label = "WING CONFIG"
                )

                NeoTextField(
                    value = state.maxWingspanMm,
                    onValueChange = { viewModel.updateMission(state.competitionMode, state.wingConfiguration, it, state.maxPropInch) },
                    label = "MAX WINGSPAN (MM)",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                NeoTextField(
                    value = state.maxPropInch,
                    onValueChange = { viewModel.updateMission(state.competitionMode, state.wingConfiguration, state.maxWingspanMm, it) },
                    label = "MAX PROP (INCH)",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    }
}

@Composable
fun WeightStep(viewModel: WizardViewModel) {
    val state by viewModel.state.collectAsState()

    StepContainer(title = "Weight", stepNumber = 2, color = NeoCyan) {
        NeoCard {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                NeoTextField(
                    value = state.emptyWeightG,
                    onValueChange = { viewModel.updateWeight(it, state.payloadWeightG) },
                    label = "EMPTY WEIGHT (G)",
                    hint = "Weigh your battery, motor, ESC, servos, and receiver together, then add estimated foam/balsa weight.",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                NeoTextField(
                    value = state.payloadWeightG,
                    onValueChange = { viewModel.updateWeight(state.emptyWeightG, it) },
                    label = "PAYLOAD (G)",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                
                Text(
                    text = "TOTAL: ${String.format("%.1f", state.totalWeightG)} g",
                    style = MaterialTheme.typography.titleLarge
                )
                
                Text(
                    text = "FORCE: ${String.format("%.2f", state.weightForceNewtons)} N",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun PropulsionStep(viewModel: WizardViewModel) {
    val state by viewModel.state.collectAsState()

    StepContainer(title = "Power", stepNumber = 3, color = NeoPink) {
        NeoCard {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                NeoTextField(
                    value = state.motorKv,
                    onValueChange = { viewModel.updatePropulsion(it, state.batteryVoltage, state.propPitchInch, state.propDiameterInch) },
                    label = "MOTOR KV",
                    hint = "This is the RPM per Volt, usually printed on the motor casing (e.g., 920KV, 2300KV).",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                NeoTextField(
                    value = state.batteryVoltage,
                    onValueChange = { viewModel.updatePropulsion(state.motorKv, it, state.propPitchInch, state.propDiameterInch) },
                    label = "VOLTAGE (V)",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                NeoTextField(
                    value = state.propDiameterInch,
                    onValueChange = { viewModel.updatePropulsion(state.motorKv, state.batteryVoltage, state.propPitchInch, it) },
                    label = "PROP DIAMETER (IN)",
                    hint = "Look for raised numbers on the front of your propeller. Example: '10x6' means 10-inch diameter.",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                NeoTextField(
                    value = state.propPitchInch,
                    onValueChange = { viewModel.updatePropulsion(state.motorKv, state.batteryVoltage, it, state.propDiameterInch) },
                    label = "PROP PITCH (IN)",
                    hint = "Example: '10x6' means 6-inch pitch.",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }

        NeoCard {
             Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("ESTIMATES", style = MaterialTheme.typography.titleLarge)
                Text("RPM: ${String.format("%.0f", state.rpm)}")
                Text("Pitch Speed: ${String.format("%.1f", state.pitchSpeedMs)} m/s")
                Text("Static Thrust: ${String.format("%.0f", state.staticThrustG)} g")
                
                if (state.isUnderpowered) {
                     Text(
                        text = "WARNING: UNDERPOWERED! Thrust < Weight",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
             }
        }
    }
}

@Composable
fun WingSizingStep(viewModel: WizardViewModel) {
    val state by viewModel.state.collectAsState()

    StepContainer(title = "Wing", stepNumber = 4, color = NeoGreen) {
        NeoCard {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                ResultItem("Required Area", String.format("%.2f dm²", state.requiredWingAreaM2 * 100))
                ResultItem("Root Chord", String.format("%.0f mm", state.rootChordMm))
                ResultItem("Wing Loading", String.format("%.1f g/dm²", state.wingLoading))
            }
        }
    }
}

@Composable
fun TailSizingStep(viewModel: WizardViewModel) {
    val state by viewModel.state.collectAsState()

    StepContainer(title = "Tail", stepNumber = 5) {
        NeoCard {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                 ResultItem("H-Stab Area", String.format("%.1f cm²", state.hStabAreaCm2))
                 ResultItem("V-Stab Area", String.format("%.1f cm²", state.vStabAreaCm2))
                 ResultItem("Moment Arm", String.format("%.0f mm", state.tailMomentArmMm))
            }
        }
    }
}

@Composable
fun LayoutStep(viewModel: WizardViewModel) {
    val state by viewModel.state.collectAsState()

    StepContainer(title = "Layout", stepNumber = 6, color = NeoCyan) {
        NeoCard {
             Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                 ResultItem("Wing LE Position", "${String.format("%.0f", state.wingLeadingEdgePosMm)} mm from nose")
                 ResultItem("CG Position", "${String.format("%.0f", state.cgPosMm)} mm from LE")
                 
                 val advice = when (state.wingConfiguration) {
                     WingConfiguration.HIGH_WING -> "Mount wing on TOP of fuselage. This provides natural pendulum stability. Ensure landing gear is tall enough to prevent prop strikes."
                     WingConfiguration.MID_WING -> "Wing must pass THROUGH the fuselage center line. Ensure your internal spars carry through the fuselage box for strength."
                     WingConfiguration.LOW_WING -> "Mount wing to BOTTOM of fuselage. You must add 'Dihedral' (upward angle) to the wings for stability, otherwise the plane will be hard to control."
                 }
                 
                 Text("CONFIGURATION ADVICE", style = MaterialTheme.typography.titleLarge)
                 Text(advice, style = MaterialTheme.typography.bodyLarge)
             }
        }
    }
}

@Composable
fun ResultItem(label: String, value: String) {
    Column {
        Text(label.uppercase(), style = MaterialTheme.typography.labelSmall)
        Text(value, style = MaterialTheme.typography.displayMedium)
    }
}
