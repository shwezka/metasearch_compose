package com.example.metasearch_compose.screens

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metasearch_compose.R
import com.example.metasearch_compose.parts.robotoFamily
import com.example.metasearch_compose.firebase_parts.login
import com.example.metasearch_compose.parts.BottomRowWithAButton
import com.example.metasearch_compose.parts.EmailInput
import com.example.metasearch_compose.parts.HeaderText
import com.example.metasearch_compose.parts.ParagraphText
import com.example.metasearch_compose.parts.PassInput


@Composable
fun LoginPage(
    onNavigateToReg: () -> Unit,
    onNavigateToRecovery: () -> Unit,
    onNavigateToProfile: () -> Unit,
) {
    var emailInput by remember { mutableStateOf("") }
    var passInput by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var checkboxChecked by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(false) }
    var emailValidation by remember { mutableStateOf(false)}


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 38.dp, start = 24.dp, end = 24.dp, bottom = 0.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(textId = R.string.welcome_message)
            Spacer(modifier = Modifier.height(8.dp))
            ParagraphText(textId = R.string.login_email_pass)
            Spacer(modifier = Modifier.height(28.dp))
            emailValidation = Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
            EmailInput( textId = R.string.email_label, emailInput = emailInput, lambda = {emailInput = it}, emailValidation = emailValidation)
            Spacer(modifier = Modifier.height(24.dp))
            PassInput(textId = R.string.pass_label, passInput = passInput, lambda = {passInput = it}, passwordVisible = passwordVisible, passCheckLambda = {passwordVisible = !passwordVisible})
            Row {
                Checkbox(
                    checked = checkboxChecked,
                    onCheckedChange = { checkboxChecked = !checkboxChecked },
                    modifier = Modifier
                        .size(20.dp)
                        .padding(top = 23.dp, start = 3.dp),

                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.rememver_me),
                    fontFamily = robotoFamily,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.black),
                    modifier = Modifier.padding(vertical = 13.dp),
                )
                Spacer(modifier = Modifier.width(80.dp))
                Text(
                    text = stringResource(R.string.forgot_your_pass),
                    fontFamily = robotoFamily,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .clickable { onNavigateToRecovery() },
                    color = colorResource(id = R.color.linkColor)
                )
            }
            isButtonEnabled = emailValidation && passInput!= ""
            Spacer(modifier = Modifier.height(310.dp))
            BottomRowWithAButton(
                lambda = {
                    login(emailInput, passInput)
                    onNavigateToProfile()
                },
                buttonTextId = R.string.login_butt_text,
                bottomRowTextId = R.string.i_dont_have_an_account,
                bottomRowTextLinkId =R.string.create_account_link,
                isButtonEnabled = isButtonEnabled,
                textLambda = {onNavigateToReg()}
            )
        }
    }
}