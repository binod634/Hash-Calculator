package com.example.hashcalculator.uiLayout

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.hashcalculator.hashAlgorithm.AlgorithmData
import com.example.hashcalculator.hashAlgorithm.algorithmList

@Composable
fun ShowAlgorithmDropDownMenu() {
    val showDropDownMenu = remember {
        mutableStateOf(false)
    }
    val selectedAlgorithm = remember {
        mutableStateOf(algorithmList.first())
    }
    Column {
        Button(
            onClick = { showDropDownMenu.value = !showDropDownMenu.value },
            shape = if (showDropDownMenu.value) RectangleShape else ButtonDefaults.shape
        ) {
            Text(text = selectedAlgorithm.value.name)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
        }
        DropdownMenu(
            expanded = showDropDownMenu.value,
            onDismissRequest = { showDropDownMenu.value = false }) {
            algorithmList.forEachIndexed { _: Int, algorithm: AlgorithmData ->
                DropdownMenuItem(
                    text = { Text(text = algorithm.name) },
                    onClick = {
                        selectedAlgorithm.value = algorithm;showDropDownMenu.value = false
                    },
                    enabled = algorithm.isAvailable
                )
            }
        }
    }
}