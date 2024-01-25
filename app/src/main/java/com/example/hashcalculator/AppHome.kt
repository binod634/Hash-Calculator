package com.example.hashcalculator

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hashcalculator.hashAlgorithm.AlgorithmData
import com.example.hashcalculator.uiLayout.HashFromFile
import com.example.hashcalculator.uiLayout.HashFromText
import com.example.hashcalculator.uiLayout.HashType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHome(appViewModel: AppViewModel) {
    val context = LocalContext.current
    val gotHash = appViewModel.calculatedHash.value
    val currentlySelectedTabIndex = remember { mutableIntStateOf(0) }
    val tabs = listOf(HashType.Text.name, HashType.File.name)
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(titleContentColor = MaterialTheme.colorScheme.onPrimary),
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.darkmode),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .aspectRatio(1f)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
                    }
                },
                title = { Text(text = "Hash\nCalculator", fontWeight = FontWeight.Black) },
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                TabRow(
                    selectedTabIndex = currentlySelectedTabIndex.intValue,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = currentlySelectedTabIndex.intValue == index,
                            onClick = { currentlySelectedTabIndex.intValue = index; appViewModel.clearHash() },
                            modifier = Modifier.height(50.dp)
                        ) {
                            Text(
                                text = title, fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                when (currentlySelectedTabIndex.intValue) {
                    0 -> HashFromText(
                        gotHash,
                        selectedAlgorithm = appViewModel.currentlySelectedAlgorithm.value,
                        changeAlgorithm = { algorithmData: AlgorithmData ->
                            appViewModel.changeAlgorithm(algorithmData)
                        }) { str: String ->
                        scope.launch {
                            appViewModel.calculateFromText(str)
                        }
                    }

                    1 -> HashFromFile(
                        gotHash,
                        selectedAlgorithm = appViewModel.currentlySelectedAlgorithm.value,
                        changeAlgorithm = { algorithmData: AlgorithmData ->
                            appViewModel.changeAlgorithm(algorithmData)
                        }) { uri:Uri? ->
                        scope.launch {
                            appViewModel.calculateFromFile(uri,context)
                        }
                    }
                }
            }
        }
    }
}