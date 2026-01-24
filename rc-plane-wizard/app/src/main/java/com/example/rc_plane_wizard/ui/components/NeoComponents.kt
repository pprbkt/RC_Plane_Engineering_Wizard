package com.example.rc_plane_wizard.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rc_plane_wizard.ui.theme.NeoBlack
import com.example.rc_plane_wizard.ui.theme.NeoWhite

@Composable
fun NeoButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val shadowOffset = if (isPressed) 0.dp else 4.dp
    val contentOffset = if (isPressed) 4.dp else 0.dp

    Box(
        modifier = modifier.height(56.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .offset(x = 4.dp, y = 4.dp)
                .background(NeoBlack)
        )
        
        Button(
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = NeoBlack,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.DarkGray
            ),
            border = BorderStroke(2.dp, NeoBlack),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .offset(x = if (isPressed) 4.dp else 0.dp, y = if (isPressed) 4.dp else 0.dp),
            interactionSource = interactionSource
        ) {
            Text(
                text = text.uppercase(),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    hint: String? = null
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog && hint != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "Technical Hint",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = { Text(text = hint) },
            confirmButton = {
                NeoButton(
                    onClick = { showDialog = false },
                    text = "GOT IT",
                    modifier = Modifier.width(120.dp)
                )
            },
            containerColor = NeoWhite,
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier.border(2.dp, NeoBlack)
        )
    }

    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = 4.dp, y = 4.dp)
                    .background(NeoBlack)
            )

            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text(label, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = NeoWhite,
                    unfocusedContainerColor = NeoWhite,
                    focusedTextColor = NeoBlack,
                    unfocusedTextColor = NeoBlack,
                    focusedBorderColor = NeoBlack,
                    unfocusedBorderColor = NeoBlack,
                    cursorColor = NeoBlack,
                ),
                shape = RoundedCornerShape(0.dp),
                trailingIcon = if (hint != null) {
                    {
                        IconButton(onClick = { showDialog = true }) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Hint",
                                tint = NeoBlack
                            )
                        }
                    }
                } else null,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, NeoBlack)
            )
        }
    }
}

@Composable
fun NeoDropdown(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = 4.dp, y = 4.dp)
                    .background(NeoBlack)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(NeoWhite)
                    .border(2.dp, NeoBlack)
                    .clickable { expanded = true }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(text = label, style = MaterialTheme.typography.labelSmall, color = NeoBlack)
                    Text(text = selectedOption, style = MaterialTheme.typography.bodyLarge, color = NeoBlack)
                }
            }
            
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(NeoWhite)
                    .border(2.dp, NeoBlack)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.uppercase(), style = MaterialTheme.typography.bodyLarge) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NeoCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = NeoWhite,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = 4.dp, y = 4.dp)
                .background(NeoBlack)
        )
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .border(2.dp, NeoBlack)
                .padding(16.dp)
        ) {
            content()
        }
    }
}
