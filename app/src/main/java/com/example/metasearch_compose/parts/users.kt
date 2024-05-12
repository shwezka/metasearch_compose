package com.example.metasearch_compose.parts

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState

data class Users(
    val name: String,
    val phone: String,
    val birthDate: String,
    val pictureUri: Uri
)