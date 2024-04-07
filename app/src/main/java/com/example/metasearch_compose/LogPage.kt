package com.example.metasearch_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun LoginPage() {
    var emailInput by remember { mutableStateOf("") }
    var passInput by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 38.dp, horizontal = 24.dp)
    ) {
        Text(
            text = "Добро пожаловать",
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
            )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Заполните почту и пароль, чтобы продолжить",
            fontFamily = robotoFamily,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(text = "Почта",)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = emailInput,
            onValueChange ={emailInput = it},
            modifier = Modifier
                .width(342.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = {Text(
                text = "***********@mail.com",
                color = Color.Gray
            )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Пароль",)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = passInput,
            onValueChange ={passInput = it},
            modifier = Modifier
                .width(342.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = {Text(
                text = "***********",
                color = Color.Gray
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if(passwordVisible) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Скрыть пароль" else "Показать пароль"


                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image,description)

                }
            }
        )
    }
}