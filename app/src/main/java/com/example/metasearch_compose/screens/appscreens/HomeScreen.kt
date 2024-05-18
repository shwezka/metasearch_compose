package com.example.metasearch_compose.screens.appscreens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.metasearch_compose.R
import com.example.metasearch_compose.parts.HeaderText
import com.example.metasearch_compose.parts.News
import com.example.metasearch_compose.parts.NewsCard
import com.example.metasearch_compose.parts.NewsTextCard
import com.example.metasearch_compose.parts.robotoFamily
import java.util.Vector
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun HomeScreen(
    newsVector: Vector<News>,
    navHostController: NavHostController
) {
    Log.d(TAG, "Полученный вектор:$newsVector")
    var dropMenuExp by remember { mutableStateOf(false) }
    var viewType by remember { mutableIntStateOf(1) }
    var dropDownMenuIconId by remember { mutableIntStateOf(R.drawable.cards_view2) }
    var onlyMediaVector by remember { mutableStateOf(Vector<News>()) }
    var mediaCounter by remember { mutableStateOf(0) }

//    for (i in 0 .. newsVector.size) {
//        if (newsVector[i].newsImage!="") {
//            mediaCounter++
//        }
//    }
//
//    onlyMediaVector.setSize(mediaCounter)
//    Log.d(TAG, "onlyMediaVector size: ${onlyMediaVector.size}")

    Log.d(TAG, "Размер вектора с новостями: ${newsVector.size}")
    Log.d(TAG, "Размер вектора с новостями у которых есть картинка: ${newsVector.filter { it.newsImage!="" }.size}")



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
                    painter = painterResource(id = dropDownMenuIconId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                        .padding(0.dp)
                        .clickable { dropMenuExp = true }
                )
                DropdownMenu(
                    expanded = dropMenuExp,
                    onDismissRequest = { dropMenuExp = false },
                    modifier = Modifier
                        .width(346.dp)
                        .height(227.dp)
                        .padding(top = 18.dp, start = 8.dp, end = 8.dp, bottom = 10.dp),

                    ){
                    Text(
                        text = "СЕТКА ОТОБРАЖЕНИЯ",
                        fontFamily = robotoFamily,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(18.dp))
                    Row(
                        modifier = Modifier
                            .clickable {
                                viewType = 1
                                dropMenuExp = false
                                dropDownMenuIconId = R.drawable.cards_view2
                            }
                    ) {
                        if(viewType == 1){
                            Image(
                                painter = painterResource(id = R.drawable.card_view_bold),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(18.dp))
                            Text(
                                text = "Card",
                                fontFamily = robotoFamily,
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight(700)
                            )
                        } else{
                            Image(
                                painter = painterResource(id = R.drawable.cards_view2),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(18.dp))
                            Text(
                                text = "Card",
                                fontFamily = robotoFamily,
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight(400)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Row(
                        modifier = Modifier
                            .clickable {
                                viewType = 2
                                dropMenuExp = false
                                dropDownMenuIconId = R.drawable.textview
                            }
                    ) {
                        if(viewType == 2){
                            Image(
                                painter = painterResource(id = R.drawable.text_view_bold),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(18.dp))
                            Text(
                                text = "Text",
                                fontFamily = robotoFamily,
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight(700)
                            )
                        }
                        else{
                            Image(
                                painter = painterResource(id = R.drawable.textview),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(18.dp))
                            Text(
                                text = "Text",
                                fontFamily = robotoFamily,
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight(400)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Row(
                        modifier = Modifier
                            .clickable {
                                viewType = 3
                                dropMenuExp = false
                                dropDownMenuIconId = R.drawable.media_view
                            }
                    ) {
                        if(viewType == 3){
                            Image(
                                painter = painterResource(id = R.drawable.media_view_bold),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(18.dp))
                            Text(
                                text = "Media",
                                fontFamily = robotoFamily,
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight(700)
                            )
                        } else{
                            Image(
                                painter = painterResource(id = R.drawable.media_view),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(18.dp))
                            Text(
                                text = "Media",
                                fontFamily = robotoFamily,
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight(400)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(13.dp))
            if(viewType == 3){
                val filteredNewsVector = newsVector.filter { it.newsImage!=""}

                LazyColumn {
                    Log.d(TAG, "Размер вектора с картинками ${filteredNewsVector.size}")
                    Log.d(TAG, "Вектор с картинками $filteredNewsVector")
                    items(filteredNewsVector.size) { index ->
                        val newsItem = filteredNewsVector.getOrNull(index)
                        newsItem?.let {
                            if (newsItem.newsImage != "") {
                                NewsCard(
                                    it,
                                    lambda = { navHostController.navigate("fullNew/${it.newsId}") }
                                )

                                Log.d("shw", "$index")
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
            LazyColumn {
                items(newsVector.size) { index ->

                    val newsItem = newsVector.getOrNull(index)
                    newsItem?.let {
                        if(viewType ==1){
                            NewsCard(
                                it,
                                lambda = { navHostController.navigate("fullNew/${it.newsId}") }
                            )
                            Log.d("shw", "$index")
                        }
                        if(viewType == 2){
                            NewsTextCard(
                                it,
                                lambda = { navHostController.navigate("fullNew/${it.newsId}") }
                            )
                            Log.d("shw", "$index")
                        }

                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

        }
    }
}