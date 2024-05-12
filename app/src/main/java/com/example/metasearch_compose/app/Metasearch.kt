package com.example.metasearch_compose.app

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.metasearch_compose.screens.LoginPage
import com.example.metasearch_compose.screens.ProfileEdit
import com.example.metasearch_compose.screens.RecoveryScreen
import com.example.metasearch_compose.screens.RegScreen

@Composable
fun MetasearchApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
    }
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "profSet") {
        composable(
            route = "login",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        )
        {
            LoginPage(
                onNavigateToReg = { navController.navigate("reg") },
                onNavigateToRecovery = { navController.navigate("recovery") },
                onNavigateToProfileSet = {navController.navigate("profSet")}
            )
        }
        composable(
            route = "reg",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ) {
            RegScreen(
                onNavigateToLog = { navController.navigate("login") },
                onNavigateToProfileSet = {navController.navigate("profSet")}
            )
        }
        composable(
            route = "recovery",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ) {
            RecoveryScreen(
                onNavigateToLog = { navController.navigate("login") },
            )
        }
        composable(
            route = "profSet",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
            ){
            ProfileEdit()
        }
    }
}