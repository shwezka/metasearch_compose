package com.example.metasearch_compose.screens.appscreens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.DropdownMenu
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.metasearch_compose.R
import com.example.metasearch_compose.firebase_parts.getFavs
import com.example.metasearch_compose.parts.FavsCard
import com.example.metasearch_compose.parts.HeaderText
import com.example.metasearch_compose.parts.News
import com.example.metasearch_compose.parts.NewsCard
import com.example.metasearch_compose.parts.NewsTextCard
import com.example.metasearch_compose.parts.robotoFamily
import java.util.Vector
import com.example.metasearch_compose.parts.NewsImageCard

@Composable
fun FavScreen(
//    newsVector: Vector<News>,
    navHostController: NavHostController
) {
    var newsVector by remember { mutableStateOf(Vector<News>()) }
    LaunchedEffect(key1 = true) {
        getFavs { favNewsData->
            newsVector.setSize(favNewsData.size)
            newsVector = favNewsData
        }
    }
    Log.d(TAG, "Полученный вектор:$newsVector")

    Log.d(TAG, "Размер вектора с новостями: ${newsVector.size}")



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 38.dp, start = 24.dp, end = 24.dp, bottom = 0.dp),
        ){
            Row(
            ){
                HeaderText(textId = R.string.favs_list)
                Spacer(modifier = Modifier.width(120.dp))

            }
            Spacer(modifier = Modifier.height(13.dp))
            LazyColumn {
                items(newsVector.size) { index ->
                    val newsItem = newsVector.getOrNull(index)
                    newsItem?.let {
                        Log.d("shw", "$index")
                            FavsCard(
                                it,
                                lambda = { navHostController.navigate("fullFavNew/${it.newsId}") }
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            HorizontalDivider(Modifier.fillMaxWidth())
                            Spacer(modifier = Modifier.height(12.dp))
                            Log.d("shw", "$index")
                    }

                }
            }

        }
    }
}