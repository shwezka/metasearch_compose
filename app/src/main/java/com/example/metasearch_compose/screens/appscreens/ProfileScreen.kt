package com.example.metasearch_compose.screens.appscreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.metasearch_compose.R
import com.example.metasearch_compose.firebase_parts.getDataFromDB
import com.example.metasearch_compose.parts.Users
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.transform.RoundedCornersTransformation
import com.example.metasearch_compose.app.robotoFamily
import com.example.metasearch_compose.parts.ProfileButtText
import com.example.metasearch_compose.parts.ProfileHeaderText
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ProfileScreen(onNavigateToLog: () -> Unit) {
    val defaultImageResourceId = R.drawable.def_avatar
    var user by remember { mutableStateOf(Users()) }

    LaunchedEffect(key1 = true) {
        getDataFromDB { userData ->
            user = userData
        }
    }

        val profilePic = rememberImagePainter(
            data = user.pictureUri,
            builder = {
                crossfade(true)
                transformations(RoundedCornersTransformation(32.0F))
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
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                if(user.pictureUri != ""){
                    Image(
                        painter = profilePic,
                        contentDescription = null,
                        modifier = Modifier.size(121.dp)
                    )
                }else{
                    Image(
                        painter = painterResource(id = defaultImageResourceId),
                        contentDescription =null,
                        modifier = Modifier.size(121.dp)
                        )
                }
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = user.name,
                    fontFamily = robotoFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 32.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = colorResource(id = R.color.sub_text)
            )
            Spacer(Modifier.height(25.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                ProfileHeaderText(textId = R.string.history)
                ProfileButtText(textId = R.string.read_news)
                ProfileButtText(textId = R.string.black_list)
                Spacer(modifier = Modifier.height(3.dp))
                ProfileHeaderText(textId = R.string.settings)
                ProfileButtText(textId = R.string.profile_edit)
                ProfileButtText(textId = R.string.privacy_policy)
                ProfileButtText(textId = R.string.offline_reading)
                ProfileButtText(textId = R.string.about_us)
                OutlinedButton(
                    onClick = {
                        FirebaseAuth.getInstance().signOut()
                        onNavigateToLog()
                    },
                    border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.error)),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.logOut_button),
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.error)
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))
            }

        }
    }
}

