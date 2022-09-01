package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.ui.theme.MyApplicationTheme

//Test cases
// 1) Click on first input and press next and verify you see all 20 items above keyboard.  If you go past 20 for now it goes back to 1 and shows it.  We should fix this in the AppTextField
// 2) Click on last TextField and verify its shown
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                //TextFieldWithOutBottomBarWorking()
                TextFieldWithBottomBarWorking()
            }
        }
    }
}