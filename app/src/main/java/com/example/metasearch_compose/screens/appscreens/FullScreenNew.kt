package com.example.metasearch_compose.screens.appscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.metasearch_compose.parts.News
import com.example.metasearch_compose.parts.NewsHeaderText
import com.example.metasearch_compose.parts.NewsProvider
import com.example.metasearch_compose.parts.NewsText
import com.example.metasearch_compose.parts.NewsUpperRow

@Preview(showBackground = true)
@Composable
fun FullScreenNew(
    @PreviewParameter(NewsProvider::class) news: News
//    news: News
){
    val sourcePic = rememberImagePainter(
        data = news.source.sourcePic,
        builder = {
            crossfade(true)
        }
    )
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
            }
            NewsText(text = news.newsText)
        }
    }
}