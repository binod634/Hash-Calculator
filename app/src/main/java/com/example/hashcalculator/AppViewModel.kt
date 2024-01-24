package com.example.hashcalculator

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hashcalculator.hashAlgorithm.CalculateHashImpl
import com.example.hashcalculator.hashAlgorithm.algorithmList
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    val calculatedHash = mutableStateOf<String?>(null)
    private val currentlySelectedAlgorithm = mutableStateOf(algorithmList.first())
    private val hashCalculator = CalculateHashImpl()
    fun calculateFromText(str: String) {
        calculatedHash.value = hashCalculator.fromText(str, currentlySelectedAlgorithm.value)
    }

    fun calculateFromFile(uri: Uri, context: Context) {
        viewModelScope.launch {
            calculatedHash.value = hashCalculator.fromFile(
                uri = uri,
                context = context,
                algorithmData = currentlySelectedAlgorithm.value
            )
        }
    }

}