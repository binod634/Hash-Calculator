package com.example.hashcalculator.uiLayout

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import com.example.hashcalculator.R
import com.example.hashcalculator.hashAlgorithm.AlgorithmData


@Composable
fun HashFromFile(
    gotHash: String?,
    selectedAlgorithm: AlgorithmData,
    changeAlgorithm: (AlgorithmData) -> Unit,
    calculateHash: (Uri?) -> Unit
) {
    val context = LocalContext.current
    val selectedFile = remember { mutableStateOf<Uri?>(null) }
    val showUploadToast = Toast.makeText(context, "Select File.", Toast.LENGTH_SHORT)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { selectedFile.value = it })
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 128.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showUploadToast.show()
                        launcher.launch(
                            arrayOf("*/*")
                        )
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.upload), contentDescription = null)
                Text(
                    text = selectedFile.value?.lastPathSegment ?: "Select File",
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
            Row {
                ShowAlgorithmDropDownMenu(selectedAlgorithm) { algorithm: AlgorithmData ->
                    changeAlgorithm(algorithm)
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = { calculateHash(selectedFile.value) }) {
                    Text(text = "Generate")
                }
            }
            ShowHashOutput(gotHash)
        }
    }
}