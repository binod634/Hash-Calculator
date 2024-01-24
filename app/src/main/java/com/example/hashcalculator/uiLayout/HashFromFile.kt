package com.example.hashcalculator.uiLayout

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hashcalculator.R


@Composable
fun HashFromFile() {
    val context = LocalContext.current
    val selectedFile = remember { mutableStateOf<Uri?>(null) }
    val showButtonToast = Toast.makeText(context, "Button Clicked.", Toast.LENGTH_SHORT)
    val showUploadToast = Toast.makeText(context, "Select File.", Toast.LENGTH_SHORT)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { selectedFile.value = it })
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 160.dp),
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
                ShowAlgorithmDropDownMenu()
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = { showButtonToast.show() }) {
                    Text(text = "Generate")
                }
            }
        }
    }
}