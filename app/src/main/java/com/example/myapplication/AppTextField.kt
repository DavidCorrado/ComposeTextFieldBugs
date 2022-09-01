package com.example.myapplication

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppTextField(modifier: Modifier = Modifier, label: @Composable (() -> Unit)? = null) {
    val text = remember { mutableStateOf("") }
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(label = label, modifier = modifier, value = text.value,
        onValueChange = { text.value = it },
    singleLine = false,
        keyboardActions = KeyboardActions(
            onDone = {
                softwareKeyboardController?.hide()
            }
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),//TODO handle next and done for last
    maxLines = 1)
}