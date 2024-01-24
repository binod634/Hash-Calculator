package com.example.hashcalculator.uiLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.hashcalculator.hashAlgorithm.AlgorithmData
import com.example.hashcalculator.hashAlgorithm.algorithmList

@Composable
fun FeatureDevelopment() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Title", fontSize = 32.sp, fontWeight = FontWeight.Black)
                Text(text = "Description")
            }
        }
    }
}

@Composable
fun TestOptions() {
    val showDropDownMenu = remember {
        mutableStateOf(false)
    }
    val selectedAlgorithm = remember {
        mutableStateOf(algorithmList.first())
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(top = 150.dp)) {
            Button(onClick = { showDropDownMenu.value = !showDropDownMenu.value }, shape = if (showDropDownMenu.value) RectangleShape else CircleShape) {
                Text(text = selectedAlgorithm.value.name)
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
}


@Composable
fun DialogTest() {
    val showDropDownMenu = remember {
        mutableStateOf(false)
    }
    val dialog = remember { mutableStateOf(false) }
    if (dialog.value) {
        FeatureDevelopment()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { dialog.value = true }) {
            Text(text = "Click me to show dialog box.")
        }
    }
}


@Preview
@Composable
fun ShowDialog() {
    TestOptions()
}