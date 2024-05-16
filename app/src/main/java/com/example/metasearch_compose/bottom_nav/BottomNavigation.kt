package com.example.metasearch_compose.bottom_nav

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.metasearch_compose.R
import com.example.metasearch_compose.parts.robotoFamily

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val bottomBarItems = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Favs,
        Screen.Profile,
    )
    BottomNavigation(
        backgroundColor = Color.White,
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        bottomBarItems.forEach{item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.nameId),
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight(400),
                        fontSize = 12.sp
                    )
                },
                enabled = true,
                selectedContentColor = colorResource(id = R.color.accent),
                unselectedContentColor = colorResource(id = R.color.sub_text)
            )
        }
    }
}