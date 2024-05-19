package com.example.metasearch_compose.bottom_nav


import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.metasearch_compose.parts.News
import com.example.metasearch_compose.parts.Users
import com.example.metasearch_compose.screens.LoginPage
import com.example.metasearch_compose.screens.ProfileEdit
import com.example.metasearch_compose.screens.ProfileSet
import com.example.metasearch_compose.screens.RecoveryScreen
import com.example.metasearch_compose.screens.RegScreen
import com.example.metasearch_compose.screens.appscreens.FavScreen
import com.example.metasearch_compose.screens.appscreens.FullFavScreenNew
import com.example.metasearch_compose.screens.appscreens.FullScreenNew
import com.example.metasearch_compose.screens.appscreens.HomeScreen
import com.example.metasearch_compose.screens.appscreens.ProfileScreen
import com.example.metasearch_compose.screens.appscreens.SearchScreen
import java.util.Vector

@Composable
fun NavGraph(
    navHostController: NavHostController,
    entryPoint: String,
    user: Users,
    newsVector: Vector<News>,
    favNewsVector: Vector<News>
){
    NavHost(navController = navHostController, startDestination = entryPoint) {
        composable(
            route = "login",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        )
        {
            LoginPage(
                onNavigateToReg = { navHostController.navigate("reg") },
                onNavigateToRecovery = { navHostController.navigate("recovery") },
                onNavigateToProfile = {navHostController.navigate("prof")}
            )
        }
        composable(
            route = "reg",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ) {
            RegScreen(
                onNavigateToLog = { navHostController.navigate("login") },
                onNavigateToProfileSet = {navHostController.navigate("profSet")}
            )
        }
        composable(
            route = "recovery",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ) {
            RecoveryScreen(
                onNavigateToLog = { navHostController.navigate("login") },
            )
        }
        composable(
            route = "profSet",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ){
            ProfileSet(
                onNavigateToProf = {navHostController.navigate("prof")}
            )
        }
        composable(
            route = "zat",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ){

        }
        composable(
            route = "prof",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ){
            ProfileScreen(
                onNavigateToLog = { navHostController.navigate("login") },
                onNavigateToEdit = {navHostController.navigate("profEdit")},
                user
            )
        }
        composable(
            route = "home",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ){
//            backStackEntry ->
//            val index = backStackEntry.arguments!!.getString("index")?.toInt()
//            Log.d("vl", "input 1) ${index}, 2) ${backStackEntry.arguments!!.getString("index")}")
            HomeScreen(
                newsVector,
                navHostController
            )
        }
        composable(
            route = "search",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ){
            SearchScreen(
                navHostController
            )
        }
        composable(
            route = "favs",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ){
            FavScreen(
//                favNewsVector,
                navHostController
            )
        }
        composable(
            route = "fullFavNew/{index}",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")
            if (index != null) {
                FullFavScreenNew(index)
            }
        }
        composable(
            route = "profEdit",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ){
            ProfileEdit(
                user,
                onNavigateToProf = {navHostController.navigate("prof")}
            )
        }
        composable(
            route = "fullNew/{index}",
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) }
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")
            if (index != null) {
                FullScreenNew(index)
            }
        }
    }
}