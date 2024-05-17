package com.example.metasearch_compose.screens.appscreens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.metasearch_compose.R
import com.example.metasearch_compose.firebase_parts.getAllNews
import com.example.metasearch_compose.firebase_parts.getNews
import com.example.metasearch_compose.parts.HeaderText
import com.example.metasearch_compose.parts.News
import com.example.metasearch_compose.parts.NewsCard
import java.util.Vector

@Preview(showBackground = true)
@Composable
fun HomeScreen(newsVector: Vector<News>){
    var news by remember { mutableStateOf(News()) }
//    var newsVector by remember { mutableStateOf(Vector<News>()) }
//    newsVector.setSize(10)
//
//
//    LaunchedEffect(key1 = true) {
////        getNews { newsData ->
////            news = newsData
////
////        }
//        getAllNews { newsData->
//            newsVector = newsData
//        }
//
//    }
    Log.d(TAG, "Полученный вектор:$newsVector")
//    Log.d(TAG, "Полученная новость:$news")
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
                HeaderText(textId = R.string.news_list)
                Spacer(modifier = Modifier.width(120.dp))
                Image(
                    painter = painterResource(id = R.drawable.cards_view2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                        .padding(0.dp)
                        .clickable { }
                )
            }
            Spacer(modifier = Modifier.height(13.dp))
            LazyColumn {
                items(10) { index ->
                    val newsItem = newsVector.getOrNull(index)
                    newsItem?.let { NewsCard(it) }
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}