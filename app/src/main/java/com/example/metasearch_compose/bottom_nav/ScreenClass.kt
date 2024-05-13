package com.example.metasearch_compose.bottom_nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.metasearch_compose.R

sealed class Screen(val route: String, @StringRes val nameId: Int, @DrawableRes val iconId: Int){
    object Profile: Screen("prof", R.string.profile_bottom_bar_name, R.drawable.prof_icon)
    object Home: Screen("home", R.string.home_bottom_bar_name, R.drawable.home_icon2)
    object Search: Screen("search", R.string.search_bottom_bar_name, R.drawable.search_icon)
    object Favs: Screen("favs", R.string.favs_bottom_bar_name, R.drawable.favs_icon)
}

