package com.example.metasearch_compose.screens.appscreens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.metasearch_compose.firebase_parts.getNews
import com.example.metasearch_compose.parts.News
import com.example.metasearch_compose.parts.NewsHeaderText
import com.example.metasearch_compose.parts.NewsProvider
import com.example.metasearch_compose.parts.NewsText
import com.example.metasearch_compose.parts.NewsUpperRow
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Vector



@Preview(showBackground = true)
@Composable
fun FullScreenNew(
    newId: String?,
//    newsVector: Vector<News>
//    news: News
){
    var news by remember { mutableStateOf(News()) }
    LaunchedEffect(key1 = true) {
        if (newId != null) {
            getNews(newId = newId, callback = {newData ->
                news = newData
            })
        }
    }
    Log.d(TAG, "Полученная новость: $news")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 38.dp, start = 24.dp, end = 24.dp, bottom = 0.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            NewsUpperRow(news = news)
            NewsHeaderText(header = news.newsTitle)
            if(news.newsImage!= ""){
                val newsPic = rememberImagePainter(
                    data = news.newsImage,
                    builder = {
                        crossfade(true)
                    }
                )
                Image(
                    painter = newsPic,
                    contentDescription = null,
                    modifier = Modifier
                        .width(346.dp)
                        .height(213.dp)
                )
            }
            NewsText(text = news.newsText)
        }
    }
}