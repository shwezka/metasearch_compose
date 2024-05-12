package com.example.metasearch_compose.parts

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState

data class Users(
    var name: String = "",
    var phone: String = "",
    var birthDate: String = "",
    var pictureUri: String = ""
)