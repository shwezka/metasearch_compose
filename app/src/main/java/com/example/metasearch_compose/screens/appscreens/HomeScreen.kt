package com.example.metasearch_compose.screens.appscreens

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.metasearch_compose.firebase_parts.getNews
import com.example.metasearch_compose.parts.News

@Preview(showBackground = true)
@Composable
fun HomeScreen(){
    var news by remember { mutableStateOf(News()) }
    LaunchedEffect(key1 = true) {
        getNews { newsData ->
            news = newsData
        }

    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

//        Button(onClick = { addNews() }) {
//
//        }
        LazyColumn { item(1){
            val newsPic = rememberImagePainter(
                data = news.newsImage,
                builder = {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(32.0F))
                }
            )
            val sourcePic = rememberImagePainter(
                data = news.source.sourcePic,
                builder = {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(32.0F))
                }
            )
            Log.d(TAG, "Вот что мы получили: $news")
            Text(text = news.source.sourceName, color = Color.Black, fontSize = 32.sp)
            Image(
                painter = sourcePic,
                contentDescription =null,
                Modifier.size(151.dp)
            )
            Text(text = news.newsText)

//            Image(
//                painter = sourcePic,
//                contentDescription = null,
//                Modifier.size(151.dp)
//            )
        }

        }
    }
}