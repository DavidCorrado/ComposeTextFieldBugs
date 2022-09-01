package com.example.myapplication

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.myapplication.extensions.modifyIf
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.util.getBringIntoViewRequester
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
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
                                Text(text = "Send invitation")
                            }
                        }

                }) { paddingValues ->
                    //WithoutSolution(paddingValues = paddingValues)

                    //Solution1(paddingValues = paddingValues)

                    //Solution2(paddingValues = paddingValues)

                    Solution3MultipleInputs(paddingValues = paddingValues)

                }
            }
        }
    }
}

@Composable
fun WithoutSolution(paddingValues: PaddingValues){
    val text = remember { mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = 20.dp)
            .padding(paddingValues = paddingValues)
    ) {
        Image(
            painter =  painterResource(R.drawable.ic_dependents),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = text.value,
            onValueChange = { text.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Solution1(paddingValues: PaddingValues){
    val bringIntoViewRequester = getBringIntoViewRequester()
    val text = remember { mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = 20.dp)
            .padding(paddingValues = paddingValues)
    ) {
        Image(
            painter =  painterResource(R.drawable.ic_dependents),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = text.value,
            onValueChange = { text.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp))

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(paddingValues.calculateBottomPadding())
                .bringIntoViewRequester(bringIntoViewRequester)
        )
    }
}

@Composable
fun Solution2(paddingValues: PaddingValues){
    val text = remember { mutableStateOf("")}
    val scrollState = rememberScrollState()
    val view = LocalView.current
    val coroutineScope = rememberCoroutineScope()
    var scrollToPosition  by remember { mutableStateOf(0F) }

    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            coroutineScope.launch {
                scrollState.animateScrollTo(
                    scrollToPosition.roundToInt()
                )
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose { view.viewTreeObserver.removeOnGlobalLayoutListener(listener) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(all = 20.dp)
            .padding(paddingValues = paddingValues)
            .onGloballyPositioned { layoutCoordinates ->
                scrollToPosition =
                    scrollState.value.toFloat() + layoutCoordinates.positionInRoot().y + 160
            }
    ) {
        Image(
            painter =  painterResource(R.drawable.ic_dependents),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = text.value,
            onValueChange = { text.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Solution3MultipleInputs(paddingValues: PaddingValues){
    val bringIntoViewRequester = getBringIntoViewRequester()
    val text1 = remember { mutableStateOf("")}
    val text2 = remember { mutableStateOf("")}
    val text3 = remember { mutableStateOf("")}
    var firstTextInputHasFocus by remember { mutableStateOf(false) }
    var secondTextInputHasFocus by remember { mutableStateOf(false) }
    var thirdTextInputHasFocus by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = 20.dp)
            .padding(paddingValues = paddingValues)
    ) {
        Image(
            painter =  painterResource(R.drawable.ic_dependents),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = text1.value,
            onValueChange = { text1.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent { focusState ->
                    if (focusState.isFocused) {
                        firstTextInputHasFocus = true
                        secondTextInputHasFocus = false
                        thirdTextInputHasFocus = false
                    }
                }
                .padding(top = 30.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                firstTextInputHasFocus = false
            }))

        OutlinedTextField(
            value = text2.value,
            onValueChange = { text2.value = it },
            modifier = Modifier
                .modifyIf(firstTextInputHasFocus){
                    bringIntoViewRequester(bringIntoViewRequester)
                }
                .fillMaxWidth()
                .onFocusEvent { focusState ->
                    if (focusState.isFocused) {
                        firstTextInputHasFocus = false
                        secondTextInputHasFocus = true
                        thirdTextInputHasFocus = false
                    }
                }
                .padding(top = 30.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                secondTextInputHasFocus = false
            }))

        OutlinedTextField(
            value = text3.value,
            onValueChange = { text3.value = it },
            modifier = Modifier
                .modifyIf(secondTextInputHasFocus){
                    bringIntoViewRequester(bringIntoViewRequester)
                }
                .fillMaxWidth()
                .onFocusEvent { focusState ->
                    if (focusState.isFocused) {
                        firstTextInputHasFocus = false
                        secondTextInputHasFocus = false
                        thirdTextInputHasFocus = true
                    }
                }
                .padding(top = 30.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                thirdTextInputHasFocus = false
            }))

        Spacer(
            modifier = Modifier
                .modifyIf(thirdTextInputHasFocus){
                    bringIntoViewRequester(bringIntoViewRequester)
                }
                .fillMaxWidth()
                .height(paddingValues.calculateBottomPadding())

        )
    }
}

