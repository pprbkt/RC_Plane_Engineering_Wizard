package com.example.rcplane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rcplane.ui.components.NeoButton
import com.example.rcplane.ui.screens.LayoutStep
import com.example.rcplane.ui.screens.MissionStep
import com.example.rcplane.ui.screens.PropulsionStep
import com.example.rcplane.ui.screens.TailSizingStep
import com.example.rcplane.ui.screens.WeightStep
import com.example.rcplane.ui.screens.WingSizingStep
import com.example.rcplane.ui.theme.NeoWhite
import com.example.rcplane.ui.theme.RCPlaneEngineeringWizardTheme
import com.example.rcplane.viewmodel.WizardViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RCPlaneEngineeringWizardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = NeoWhite
                ) {
                    WizardApp()
                }
            }
        }
    }
}

enum class WizardScreen(val route: String) {
    MISSION("mission"),
    WEIGHT("weight"),
    PROPULSION("propulsion"),
    WING("wing"),
    TAIL("tail"),
    LAYOUT("layout")
}

@Composable
fun WizardApp() {
    val navController = rememberNavController()
    val viewModel: WizardViewModel = viewModel()
    
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: WizardScreen.MISSION.route
    
    // Determine current step index
    val steps = WizardScreen.values()
    val currentIndex = steps.indexOfFirst { it.route == currentRoute }
    
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Back Button
                if (currentIndex > 0) {
                    NeoButton(
                        onClick = { navController.popBackStack() },
                        text = "BACK",
                        modifier = Modifier.weight(1f),
                        containerColor = NeoWhite
                    )
                } else {
                     // Empty spacer to keep Next button on right if needed, or just occupy space
                     // But simpler to just show nothing if weight is adjusted, or show disabled button
                }
                
                // Next Button
                if (currentIndex < steps.lastIndex) {
                    NeoButton(
                        onClick = {
                            val nextStep = steps[currentIndex + 1]
                            navController.navigate(nextStep.route)
                        },
                        text = "NEXT",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = WizardScreen.MISSION.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(WizardScreen.MISSION.route) { MissionStep(viewModel) }
            composable(WizardScreen.WEIGHT.route) { WeightStep(viewModel) }
            composable(WizardScreen.PROPULSION.route) { PropulsionStep(viewModel) }
            composable(WizardScreen.WING.route) { WingSizingStep(viewModel) }
            composable(WizardScreen.TAIL.route) { TailSizingStep(viewModel) }
            composable(WizardScreen.LAYOUT.route) { LayoutStep(viewModel) }
        }
    }
}
