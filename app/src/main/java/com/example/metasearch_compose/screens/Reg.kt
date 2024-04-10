package com.example.metasearch_compose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.metasearch_compose.app.robotoFamily

@Preview(showBackground = true)
@Composable
fun RegScreen(){
    var emailInput by remember { mutableStateOf("") }
    var passInput by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var checkboxChecked by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 38.dp, start = 24.dp,  end =24.dp, bottom = 0.dp )
    ) {
        Text(
            text = "Создать аккаунт",
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Завершите регистрацию чтобы начать",
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
            placeholder = {
                Text(
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
            placeholder = {
                Text(
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
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Повторите пароль",)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = passInput,
            onValueChange ={passInput = it},
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
                val image = if(passwordVisible) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Скрыть пароль" else "Показать пароль"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image,description)
                }
            }
        )
        Spacer(modifier = Modifier.height(325.dp))
        Button(
            onClick = {},
            shape = RoundedCornerShape(12),
            modifier = Modifier
                .height(46.dp)
                .width(342.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7576D6))
        ) {
            Text(
                text = "Зарегестрироваться",
                fontFamily = robotoFamily,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        Row {
            Spacer(modifier = Modifier.width(70.dp))
            Text(text = "У меня уже есть аккаунт!")
            Text(
                text = "Войти",
                color = Color(0xFF7576D6)
            )
        }
    }
}