package com.example.metasearch_compose.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.metasearch_compose.screens.LoginPage
import com.example.metasearch_compose.screens.RegScreen

@Composable
fun MetasearchApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
    }
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login"){
        composable("login"){
            LoginPage(onNavigateToReg = { navController.navigate("reg") })
        }
        composable("reg"){
            RegScreen(onNavigateToLog = {navController.navigate("login")})
        }
    }
}