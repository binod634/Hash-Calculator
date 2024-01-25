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
import com.example.hashcalculator.hashAlgorithm.AlgorithmData
import com.example.hashcalculator.hashAlgorithm.algorithmList

@Composable
fun ShowAlgorithmDropDownMenu(selectedAlgorithm:AlgorithmData,changeAlgorithm:(AlgorithmData) -> Unit) {
    val showDropDownMenu = remember {
        mutableStateOf(false)
    }
    Column {
        Button(
            onClick = { showDropDownMenu.value = !showDropDownMenu.value },
            shape = if (showDropDownMenu.value) RectangleShape else ButtonDefaults.shape
        ) {
            Text(text = selectedAlgorithm.name)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
        }
        DropdownMenu(
            expanded = showDropDownMenu.value,
            onDismissRequest = { showDropDownMenu.value = false }) {
            algorithmList.forEachIndexed { _: Int, algorithm: AlgorithmData ->
                DropdownMenuItem(
                    text = { Text(text = algorithm.name) },
                    onClick = {
                        showDropDownMenu.value = false
                        changeAlgorithm(algorithm)
                    },
                    enabled = algorithm.isAvailable
                )
            }
        }
    }
}