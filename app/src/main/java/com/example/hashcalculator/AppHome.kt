package com.example.hashcalculator

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hashcalculator.uiLayout.HashFromFile
import com.example.hashcalculator.uiLayout.HashFromText
import com.example.hashcalculator.uiLayout.HashType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHome() {
    val currentlySelectedTabIndex = remember { mutableIntStateOf(0) }
    val tabs = listOf(HashType.Text.name, HashType.File.name)
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
                            onClick = { currentlySelectedTabIndex.intValue = index },
                            modifier = Modifier.height(50.dp)
                        ) {
                            Text(
                                text = title, fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                when (currentlySelectedTabIndex.intValue) {
                    0 -> HashFromText()
                    1 -> HashFromFile()
                }
            }
        }
    }
}