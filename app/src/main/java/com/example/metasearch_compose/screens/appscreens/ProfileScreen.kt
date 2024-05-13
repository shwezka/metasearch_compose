package com.example.metasearch_compose.screens.appscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
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
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.metasearch_compose.R
import com.example.metasearch_compose.firebase_parts.getDataFromDB
import com.example.metasearch_compose.parts.Users

@Composable
fun ProfileScreen() {
    val defaultImageResourceId = R.drawable.def_avatar
    var painter by remember { mutableStateOf<Painter?>(null) }
    var user by remember { mutableStateOf(Users()) }

    LaunchedEffect(key1 = true) {
        getDataFromDB { userData ->
            user = userData
        }
//        if (user.pictureUri != "") {
//            withContext(Dispatchers.IO, context = LocalContext.current) {
//                val stream = URL(user.pictureUri).openStream()
//                val bitmap = BitmapFactory.decodeStream(stream)
//                val drawable = BitmapDrawable(resources, bitmap)
//
//
//            }
//        }
    }

        val profilePic = rememberImagePainter(
            data = user.pictureUri,
            builder = {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Text(
            text = "Имя пользователя: ${user}",
            color = Color.Black
        )
        if(user.pictureUri != ""){
            Image(painter = profilePic, contentDescription = null)
        }else{
            Image(painter = painterResource(id = defaultImageResourceId), contentDescription =null )
        }

        }
    }

