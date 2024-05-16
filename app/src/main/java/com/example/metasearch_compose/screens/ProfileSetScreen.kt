package com.example.metasearch_compose.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Patterns
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metasearch_compose.R
import com.example.metasearch_compose.app.robotoFamily
import com.example.metasearch_compose.firebase_parts.addUserData
import com.example.metasearch_compose.parts.AppButton
import com.example.metasearch_compose.parts.LabelText
import com.example.metasearch_compose.parts.SimpleInput
import com.example.metasearch_compose.parts.Users
import java.util.Date

@Composable
fun ProfileSet(onNavigateToProf: () -> Unit) {
    var nameInput by remember { mutableStateOf("") }
    var phoneInput by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false)}
    var phoneValidation by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    var date by remember { mutableStateOf("") }

    val year: Int
    val month: Int
    val day: Int
    var stringMonth: String

    //тут обрабатывается ввод даты
    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = android.app.DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            stringMonth = when(month){
                1 -> "Января"
                2 -> "Февраля"
                3 -> "Марта"
                4 -> "Апреля"
                5 -> "Мая"
                6 -> "Июня"
                7 -> "Июля"
                8 -> "Августа"
                9 -> "Сентября"
                10 -> "Октября"
                11 -> "Ноября"
                12 -> "Декабря"
                else -> ""
            }
            date = "$dayOfMonth $stringMonth $year"
        }, year, month, day
    )

    //тут обрабатывается добавление пикчи
    var imageUri by remember{ mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent())
    { uri: Uri? ->
        imageUri = uri
    }

    imageUri?.let{
        if(Build.VERSION.SDK_INT < 28){
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)
        }else{
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }

        bitmap.value?.let{btm->
            Image(
                bitmap = btm.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(141.dp)
            )
        }
    }

    val defaultImageResourceId = R.drawable.def_avatar

    val user = imageUri?.let { Users(nameInput, phoneInput, date, it.toString()) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        if(isLoading){
            Box(
                modifier = Modifier.fillMaxSize(),
            ){}
            CircularProgressIndicator()
        }
        else{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 38.dp, start = 24.dp, end = 24.dp, bottom = 0.dp),
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(40.dp),
                    modifier = Modifier.padding(start = 15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image_selector),
                        contentDescription = null,
                        modifier = Modifier.clickable { launcher.launch("image/*") }
                    )
                    if (imageUri != null) {
                        imageUri?.let {
                            if (Build.VERSION.SDK_INT < 28) {
                                bitmap.value =
                                    MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                            } else {
                                val source = ImageDecoder.createSource(context.contentResolver, it)
                                bitmap.value = ImageDecoder.decodeBitmap(source)
                            }

                            bitmap.value?.let { btm ->
                                Image(
                                    bitmap = btm.asImageBitmap(),
                                    contentDescription = null,
                                    modifier = Modifier.size(151.dp)
                                )
                            }
                        }
                    } else {
                        Image(
                            painter = painterResource(id = defaultImageResourceId),
                            contentDescription = null,
                            modifier = Modifier.size(151.dp)
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.theme_changer_1),
                        contentDescription = null,
                        modifier = Modifier.clickable { /*TODO*/ }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                SimpleInput(
                    textId = R.string.fio_label,
                    input = nameInput,
                    lambda = { nameInput = it },
                    placeholderTextId = R.string.fio_placehlder
                )
                Spacer(modifier = Modifier.height(24.dp))
                LabelText(R.string.number_label)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = phoneInput,
                    onValueChange = { phoneInput = it },
                    modifier = Modifier
                        .width(370.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textStyle = TextStyle(
                        fontFamily = robotoFamily,
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.number_placeholder),
                            color = Color.Gray
                        )
                    },
                    isError = !phoneValidation && phoneInput != "",
                    supportingText = {
                        if (!phoneValidation && phoneInput != "") {
                            Text(
                                stringResource(id = R.string.phone_err),
                                fontFamily = robotoFamily,
                                fontSize = 14.sp
                            )
                        }
                    }
                )
                phoneValidation = Patterns.PHONE.matcher(phoneInput).matches()
                Spacer(modifier = Modifier.height(24.dp))
                LabelText(textId = R.string.birthday_label)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { datePickerDialog.show() },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .width(370.dp)
                        .height(44.dp),
                    contentPadding = PaddingValues(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                ) {
                    if (date == "") {
                        Text(
                            text = stringResource(id = R.string.birthday_select_button),
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        Text(
                            text = date,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                LabelText(textId = R.string.source_label)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .width(370.dp)
                        .height(44.dp),
                    contentPadding = PaddingValues(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                ) {
                    Text(
                        text = stringResource(id = R.string.source_select_button),
                        modifier = Modifier.fillMaxWidth()
                    )

                }
                Spacer(modifier = Modifier.height(24.dp))
                isButtonEnabled = phoneValidation && nameInput != ""
                AppButton(
                    lambda = {
                        if (user != null) {
                            isLoading = true
                            addUserData(user)
                            isLoading = false
                            onNavigateToProf()
                        }
                    },
                    isButtonEnabled = isButtonEnabled,
                    buttonTextId = R.string.continue_text
                )
            }

        }
    }
}
