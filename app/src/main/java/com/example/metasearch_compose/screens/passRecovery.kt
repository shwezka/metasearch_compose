package com.example.metasearch_compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metasearch_compose.app.robotoFamily

@Preview(showBackground = true)
@Composable
fun RecoveryScreen() {
    var emailInput by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 38.dp, start = 24.dp,  end =24.dp, bottom = 0.dp )
    ) {
        Text(
            text = "Восстановление пароля",
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Введите свою почту",
            fontFamily = robotoFamily,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(text = "Почта",)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = emailInput,
            onValueChange = { emailInput = it },
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
        Spacer(modifier = Modifier.height(520.dp))
        Button(
            onClick = {},
            shape = RoundedCornerShape(12),
            modifier = Modifier
                .height(46.dp)
                .width(342.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7576D6))
        ) {
            Text(
                text = "Отправить код",
                fontFamily = robotoFamily,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        Row {
            Spacer(modifier = Modifier.width(50.dp))
            Text(text = "Я вспомнил свой пароль!")
            Text(
                text = "Вернуться",
                color = Color(0xFF7576D6)
            )
        }
    }
}