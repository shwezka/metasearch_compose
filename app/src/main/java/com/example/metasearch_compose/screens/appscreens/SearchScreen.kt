package com.example.metasearch_compose.screens.appscreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Surface
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.metasearch_compose.R
import com.example.metasearch_compose.firebase_parts.getAllNews
import com.example.metasearch_compose.firebase_parts.getFavs
import com.example.metasearch_compose.parts.FavsCard
import com.example.metasearch_compose.parts.News
import com.example.metasearch_compose.parts.NewsTextCard
import java.util.Vector

@Composable
fun SearchScreen(navHostController: NavHostController) {
    var newsVector by remember { mutableStateOf(Vector<News>()) }
    LaunchedEffect(key1 = true) {
        getAllNews { favNewsData->
            newsVector.setSize(favNewsData.size)
            newsVector = favNewsData
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.map),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(449.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            LazyRow(
                modifier = Modifier.padding(start = 15.dp)
            ) {
                items(newsVector.size) { index ->
                    val newsItem = newsVector.getOrNull(index)
                    newsItem?.let {
                        Log.d("shw", "$index")
                        NewsTextCard(
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