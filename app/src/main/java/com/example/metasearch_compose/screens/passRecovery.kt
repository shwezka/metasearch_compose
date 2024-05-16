package com.example.metasearch_compose.screens

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.metasearch_compose.R
import com.example.metasearch_compose.firebase_parts.sendRecoveryEmail
import com.example.metasearch_compose.parts.BottomRowWithAButton
import com.example.metasearch_compose.parts.EmailInput
import com.example.metasearch_compose.parts.HeaderText
import com.example.metasearch_compose.parts.ParagraphText


@Composable
fun RecoveryScreen(onNavigateToLog: () -> Unit) {
    var emailInput by remember { mutableStateOf("") }
    var emailValidation by remember { mutableStateOf(false)}
    var isButtonEnabled by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 38.dp, start = 24.dp, end = 24.dp, bottom = 0.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(textId = R.string.password_recovery_header)
            Spacer(modifier = Modifier.height(8.dp))
            ParagraphText(textId = R.string.password_recovery_subheader)
            Spacer(modifier = Modifier.height(28.dp))
            emailValidation = Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
            EmailInput( textId = R.string.email_label, emailInput = emailInput, lambda = {emailInput = it}, emailValidation = emailValidation)
            isButtonEnabled = emailValidation
            Spacer(modifier = Modifier.height(493.dp))
            BottomRowWithAButton(
                lambda = {
                    sendRecoveryEmail(emailInput)
                    openDialog = true

                },
                buttonTextId = R.string.password_recovery_button_text,
                bottomRowTextId = R.string.password_recovery_bottom_simple_text,
                bottomRowTextLinkId = R.string.password_recovery_bottom_linker_text,
                isButtonEnabled = isButtonEnabled,
                textLambda = {onNavigateToLog()}
            )
            if (openDialog) {
                AlertDialog(
                    onDismissRequest = { openDialog = false},
                    title = { Text(text = stringResource(id = R.string.pass_reco_dialog_title))},
                    text = { Text(text = stringResource(id = R.string.pass_reco_dialog_text))},
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialog = false
                                onNavigateToLog()
                            }
                        ) {
                            Text(text = stringResource(R.string.ok))
                        }
                    }
                )
            }
        }
    }
}