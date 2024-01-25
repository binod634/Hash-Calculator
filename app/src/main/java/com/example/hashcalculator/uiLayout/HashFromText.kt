package com.example.hashcalculator.uiLayout

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.hashcalculator.hashAlgorithm.AlgorithmData

@Composable
fun HashFromText(
    gotHash: String?,
    selectedAlgorithm: AlgorithmData,
    changeAlgorithm: (AlgorithmData) -> Unit,
    calculateHash: (String) -> Unit
) {
    val txt = remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = txt.value,
                label = {
                    Text(text = "Text")
                },
                onValueChange = { txt.value = it },
                maxLines = 1
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ShowAlgorithmDropDownMenu(selectedAlgorithm) { algorithm: AlgorithmData ->
                    changeAlgorithm(algorithm)
                }
                Button(onClick = { calculateHash(txt.value) }) {
                    Text(text = "Generate")
                }
            }
            ShowHashOutput(gotHash = gotHash)
        }
    }
}