package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldWithBottomBarBroken() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "TopAppBar title")
            }
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Bottom")
                }
            }

        }) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())) {
            repeat(20) { index ->
                AppTextField(label = { Text("Test$index") })
            }
        }
    }
}