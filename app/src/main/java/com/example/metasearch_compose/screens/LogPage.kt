package com.example.metasearch_compose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metasearch_compose.R
import com.example.metasearch_compose.app.robotoFamily
import com.example.metasearch_compose.parts.BottomRowWithAButton
import com.example.metasearch_compose.parts.EmailInput
import com.example.metasearch_compose.parts.HeaderText
import com.example.metasearch_compose.parts.ParagraphText
import com.example.metasearch_compose.parts.PassInput

@Preview(showBackground = true)
@Composable
fun LoginPage() {
    var emailInput by remember { mutableStateOf("") }
    var passInput by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var checkboxChecked by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 38.dp, start = 24.dp, end = 24.dp, bottom = 0.dp)
        ) {
            HeaderText(textId = R.string.welcome_message)
            Spacer(modifier = Modifier.height(8.dp))
            ParagraphText(textId = R.string.login_email_pass)
            Spacer(modifier = Modifier.height(28.dp))
            EmailInput( textId = R.string.email_label, emailInput = emailInput, lambda = {emailInput = it})
            Spacer(modifier = Modifier.height(24.dp))
            PassInput(textId = R.string.pass_label, passInput = passInput, lambda = {passInput = it}, passwordVisible = passwordVisible, passCheckLambda = {passwordVisible = !passwordVisible})
            Row {
                Checkbox(
                    checked = checkboxChecked,
                    onCheckedChange = { checkboxChecked = !checkboxChecked },
                    modifier = Modifier.padding(0.dp)
                )
                Text(
                    text = "Запомнить меня",
                    modifier = Modifier.padding(vertical = 13.dp),
                )
                Spacer(modifier = Modifier.width(87.dp))
                Text(
                    text = "Забыли пароль",
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .clickable { },
                    color = colorResource(id = R.color.linkColor)
                )
            }
            isButtonEnabled = emailInput !="" && passInput!= ""
            Spacer(modifier = Modifier.height(300.dp))
            BottomRowWithAButton(
                lambda = { /*TODO*/ },
                buttonTextId = R.string.login_butt_text,
                bottomRowTextId = R.string.i_dont_have_an_account,
                bottomRowTextLinkId =R.string.create_account_link,
                isButtonEnabled = isButtonEnabled
            )
        }
    }
}