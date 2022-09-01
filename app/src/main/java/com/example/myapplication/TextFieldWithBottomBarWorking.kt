package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldWithBottomBarWorking() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "TopAppBar title")
            }
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier
                .fillMaxWidth()) {
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Bottom")
                }
            }

        }) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())) {
            repeat(20) { index ->
                AppTextField(label = { Text("Test${index + 1}") })
            }
        }
    }
}