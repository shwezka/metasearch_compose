package com.example.metasearch_compose.parts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metasearch_compose.R
import com.example.metasearch_compose.app.robotoFamily

@Composable
fun HeaderText(
    textId : Int
){
    Text(
        text = stringResource(id = textId),
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.Black
    )
}

@Composable
fun ParagraphText(
    textId: Int
){
    Text(
        text = stringResource(id = textId),
        fontFamily = robotoFamily,
        fontSize = 14.sp,
        color = Color.Black
    )
}

@Composable
fun LabelText(
    textId: Int
){
    Text(
        text = stringResource(id = textId),
        color = Color.Black
    )
}

@Composable
fun EmailInput(
    textId: Int,
    emailInput: String,
    lambda:(String) -> Unit,
){
    LabelText(textId = textId)
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = emailInput,
        onValueChange = lambda,
        modifier = Modifier
            .width(342.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        placeholder = {
            Text(
                text = stringResource(R.string.mail_placeholder),
                color = Color.Gray
            )
        }

    )
}

@Composable
fun PassInput(
    textId: Int,
    passInput: String,
    lambda:(String) -> Unit,
    passwordVisible: Boolean,
    passCheckLambda: () -> Unit
){
    Text(text = stringResource(R.string.pass_label))
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = passInput,
        onValueChange = lambda,
        modifier = Modifier
            .width(342.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = {
            Text(
                text = "***********",
                color = Color.Gray
            )
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Скрыть пароль" else "Показать пароль"
            IconButton(onClick = passCheckLambda) {
                Icon(imageVector = image, description)
            }
        }
    )
}
