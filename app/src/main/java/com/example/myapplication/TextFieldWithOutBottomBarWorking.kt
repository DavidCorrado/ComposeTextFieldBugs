package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldWithOutBottomBarWorking() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "TopAppBar title")
            }
        }) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())) {
            repeat(20) { index ->
                AppTextField(label = { Text("Test$index") })
            }
        }
    }
}