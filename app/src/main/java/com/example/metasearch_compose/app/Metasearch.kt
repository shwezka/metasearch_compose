package com.example.metasearch_compose.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.metasearch_compose.bottom_nav.BottomNavigationBar
import com.example.metasearch_compose.bottom_nav.NavGraph
import com.example.metasearch_compose.screens.LoginPage
import com.example.metasearch_compose.screens.ProfileEdit
import com.example.metasearch_compose.screens.RecoveryScreen
import com.example.metasearch_compose.screens.RegScreen
import com.example.metasearch_compose.screens.appscreens.FavScreen
import com.example.metasearch_compose.screens.appscreens.HomeScreen
import com.example.metasearch_compose.screens.appscreens.ProfileScreen
import com.example.metasearch_compose.screens.appscreens.SearchScreen
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MetasearchApp(){
    val navController = rememberNavController()
    val entryPoint = if(FirebaseAuth.getInstance().currentUser != null) "prof" else "login"

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    if(
        currentRoute != "login" &&
        currentRoute != "reg" &&
        currentRoute != "recovery" &&
        currentRoute != "profSet" &&
        currentRoute != "settings" // Добавлено условие для страницы "settings"
    ){
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) {
            NavGraph(navHostController = navController, entryPoint = entryPoint)
        }
    } else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            when (currentRoute) {
                "login" -> LoginPage(
                    onNavigateToReg = { navController.navigate("reg") },
                    onNavigateToRecovery = { navController.navigate("recovery") },
                    onNavigateToProfileSet = {navController.navigate("profSet")}
                )
                "reg" -> RegScreen(
                    onNavigateToLog = {navController.navigate("login")},
                    onNavigateToProfileSet = {navController.navigate("profSet")}
                )
                "recovery" -> RecoveryScreen(
                    onNavigateToLog = {navController.navigate("login")},
                )
                "profSet" -> ProfileEdit(
                    onNavigateToProf = {navController.navigate("prof")}
                )
                else -> LoginPage(
                    onNavigateToReg = { navController.navigate("reg") },
                    onNavigateToRecovery = { navController.navigate("recovery") },
                    onNavigateToProfileSet = {navController.navigate("profSet")}
                )
            }
        }
    }
}

//    val entryPoint = if(FirebaseAuth.getInstance().currentUser != null) "prof" else "login"
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = entryPoint) {
//        composable(
//            route = "login",
//            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
//            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
//        )
//        {
//            LoginPage(
//                onNavigateToReg = { navController.navigate("reg") },
//                onNavigateToRecovery = { navController.navigate("recovery") },
//                onNavigateToProfileSet = {navController.navigate("profSet")}
//            )
//        }
//        composable(
//            route = "reg",
//            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
//            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
//        ) {
//            RegScreen(
//                onNavigateToLog = { navController.navigate("login") },
//                onNavigateToProfileSet = {navController.navigate("profSet")}
//            )
//        }
//        composable(
//            route = "recovery",
//            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
//            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
//        ) {
//            RecoveryScreen(
//                onNavigateToLog = { navController.navigate("login") },
//            )
//        }
//        composable(
//            route = "profSet",
//            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
//            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
//            ){
//            ProfileEdit(
//                onNavigateToProf = {navController.navigate("prof")}
//            )
//        }
//        composable(
//            route = "zat",
//            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
//            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
//        ){
//
//        }
//        composable(
//            route = "prof",
//            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
//            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
//        ){
//            ProfileScreen(
//                onNavigateToLog = { navController.navigate("login") }
//            )
//        }
//        composable(
//            route = "home",
//            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
//            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
//        ){
//            HomeScreen()
//        }
//        composable(
//            route = "search",
//            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
//            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
//        ){
//            SearchScreen()
//        }
//        composable(
//            route = "favs",
//            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
//            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
//        ){
//            FavScreen()
//        }
//    }