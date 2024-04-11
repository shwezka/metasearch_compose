package com.example.metasearch_compose.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.metasearch_compose.screens.RegScreen

@Composable
fun MetasearchApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
//        LoginPage()
        RegScreen()
    }
}