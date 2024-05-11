package com.example.metasearch_compose.screens


import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.metasearch_compose.R
import com.example.metasearch_compose.firebase_parts.createFirebaseAccount
import com.example.metasearch_compose.parts.BottomRowWithAButton
import com.example.metasearch_compose.parts.EmailInput
import com.example.metasearch_compose.parts.HeaderText
import com.example.metasearch_compose.parts.ParagraphText
import com.example.metasearch_compose.parts.RegistrationPass


@Composable
fun RegScreen(onNavigateToLog: ()-> Unit){
    var emailInput by remember { mutableStateOf("") }
    var passInput by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var repeatPassInput by remember { mutableStateOf("") }
    var repeatPasswordVisible by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(false) }
    var emailValidation by remember { mutableStateOf(false)}

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 38.dp, start = 24.dp, end = 24.dp, bottom = 0.dp)
        ) {
            HeaderText(textId = R.string.create_an_account)
            Spacer(modifier = Modifier.height(8.dp))
            ParagraphText(textId = R.string.finish_reg)
            Spacer(modifier = Modifier.height(28.dp))
            emailValidation = !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
            EmailInput(textId = R.string.email_label, emailInput = emailInput, lambda = {emailInput = it}, validatorHasErrors = emailValidation)
            Spacer(modifier = Modifier.height(20.dp))
            RegistrationPass(
                passLabelId = R.string.pass_label,
                passInput = passInput,
                passInputLambda = {passInput = it},
                passwordVisible = passwordVisible,
                passCheckLambda = { passwordVisible = !passwordVisible },
                repeatPassLabelId = R.string.repeat_pass,
                repeatPassInput = repeatPassInput,
                repeatPassInputLambda = {repeatPassInput = it},
                repeatPasswordVisible = repeatPasswordVisible,
                repeatPassCheckLambda = { repeatPasswordVisible = !repeatPasswordVisible }
            )
            isButtonEnabled = (passInput == repeatPassInput) && (passInput!="") && (emailInput!="")
            Spacer(modifier = Modifier.height(258.dp))
            BottomRowWithAButton(
                lambda = { createFirebaseAccount(email = emailInput, password = passInput) },
                buttonTextId = R.string.register,
                bottomRowTextId = R.string.i_already_have_an_account,
                bottomRowTextLinkId = R.string.login,
                isButtonEnabled = isButtonEnabled,
                textLambda = { onNavigateToLog() }
            )
        }
    }
}


