package com.example.metasearch_compose.app

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.metasearch_compose.bottom_nav.BottomNavigationBar
import com.example.metasearch_compose.bottom_nav.NavGraph
import com.example.metasearch_compose.firebase_parts.getAllNews
import com.example.metasearch_compose.firebase_parts.getDataFromDB
import com.example.metasearch_compose.firebase_parts.getFavs
import com.example.metasearch_compose.parts.News
import com.example.metasearch_compose.parts.Users
import com.example.metasearch_compose.screens.LoginPage
import com.example.metasearch_compose.screens.ProfileSet
import com.example.metasearch_compose.screens.RecoveryScreen
import com.example.metasearch_compose.screens.RegScreen
import com.google.firebase.auth.FirebaseAuth
import java.util.Vector

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MetasearchApp(){

    var user by remember { mutableStateOf(Users()) }
    val navController = rememberNavController()
    var entryPoint by remember { mutableStateOf("login") }
    var newsVector by remember { mutableStateOf(Vector<News>()) }
    var favsVector by remember { mutableStateOf(Vector<News>()) }

    if(FirebaseAuth.getInstance().currentUser != null){
        LaunchedEffect(key1 = true) {
            getDataFromDB { userData ->
                user = userData
            }
            getAllNews { newsData->
                newsVector.setSize(newsData.size)
                newsVector = newsData
            }

//            getFavs { favNewsData->
//                favsVector.setSize(favNewsData.size)
//                favsVector = favNewsData
//            }
        }
        entryPoint = "home"
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    if(
        currentRoute != "login" &&
        currentRoute != "reg" &&
        currentRoute != "recovery" &&
        currentRoute != "profSet" &&
        currentRoute != "zat" // Добавлено условие для страницы "settings"
    ){
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) {
            NavGraph(navHostController = navController, entryPoint = entryPoint, user, newsVector, favsVector)
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
                    onNavigateToProfile = {navController.navigate("prof")},
                )
                "reg" -> RegScreen(
                    onNavigateToLog = {navController.navigate("login")},
                    onNavigateToProfileSet = {navController.navigate("profSet")}
                )
                "recovery" -> RecoveryScreen(
                    onNavigateToLog = {navController.navigate("login")},
                )
                "profSet" -> ProfileSet(
                    onNavigateToProf = {navController.navigate("prof")}
                )
                else -> LoginPage(
                    onNavigateToReg = { navController.navigate("reg") },
                    onNavigateToRecovery = { navController.navigate("recovery") },
                    onNavigateToProfile = {navController.navigate("prof")},
                )
            }
        }
    }
}

