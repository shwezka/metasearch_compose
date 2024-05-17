package com.example.metasearch_compose.parts


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.metasearch_compose.R


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
        fontFamily = robotoFamily,
        color = Color.Black
    )
}

@Composable
fun ProfileHeaderText(
    textId: Int
){
    Text(
        text = stringResource(id = textId),
        fontFamily = robotoFamily,
        fontWeight = FontWeight(600),
        fontSize = 18.sp,
        color = Color.Black
    )
}

@Composable
fun ProfileButtText(
    textId: Int
){
    Text(
        text = stringResource(id = textId),
        fontFamily = robotoFamily,
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        color = Color.Black
    )
}

@Composable
fun EmailInput(
    textId: Int,
    emailInput: String,
    lambda:(String) -> Unit,
    emailValidation: Boolean
){
    LabelText(textId = textId)
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = emailInput,
        onValueChange = lambda,
        modifier = Modifier
            .width(370.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        textStyle = TextStyle(
            fontFamily = robotoFamily,
            fontSize = 14.sp,
            color = Color.Black
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.mail_placeholder),
                color = Color.Gray
            )
        },
        isError = !emailValidation && emailInput!="",
        supportingText = {
            if(!emailValidation && emailInput!=""){
                Text(
                    stringResource(id = R.string.emailErr),
                    fontFamily = robotoFamily,
                    fontSize = 14.sp
                )
            }
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
    LabelText(textId)
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = passInput,
        onValueChange = lambda,
        modifier = Modifier
            .width(370.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        textStyle = TextStyle(
            fontFamily = robotoFamily,
            fontSize = 14.sp,
            color = Color.Black
        ),
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
        },
        isError = passInput.length < 6 && passInput!="",
        supportingText = {
            if (passInput.length< 6 && passInput!=""){
                Text(
                    text = stringResource(id = R.string.short_pass),
                    fontFamily = robotoFamily,
                    fontSize = 14.sp
                )
            }
        }
    )
}

@Composable
fun SimpleInput(
    textId: Int,
    input: String,
    placeholderTextId: Int,
    lambda:(String) -> Unit,
){
    LabelText(textId)
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = input,
        onValueChange = lambda,
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
                text = stringResource(id = placeholderTextId),
                color = Color.Gray
            )
        }
    )
}

@Composable
fun BottomRowWithAButton(
    lambda: () -> Unit,
    buttonTextId: Int,
    bottomRowTextId: Int,
    bottomRowTextLinkId: Int,
    isButtonEnabled: Boolean,
    textLambda: () -> Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Button(
            onClick = lambda,
            shape = RoundedCornerShape(12),
            modifier = Modifier
                .height(46.dp)
                .width(370.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonColor),
                disabledContainerColor = colorResource(id = R.color.disabledButtonColor)
            ),
            enabled = isButtonEnabled
        ) {
            Text(
                text = stringResource(id = buttonTextId),
                fontFamily = robotoFamily,
                fontSize = 16.sp,
                color = colorResource(id = R.color.white)
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        Row {
//            Spacer(modifier = Modifier.width(70.dp))
            Text(
                text = stringResource(id = bottomRowTextId),
                fontFamily = robotoFamily,
                fontSize = 16.sp,
                color = colorResource(id = R.color.bottomRowTextColor)
            )
            Text(
                text = stringResource(id = bottomRowTextLinkId),
                fontFamily = robotoFamily,
                fontSize = 16.sp,
                color = colorResource(id = R.color.linkColor),
                modifier = Modifier.clickable { textLambda.invoke() }
            )
        }
    }
}


@Composable
fun RegistrationPass(
    passLabelId: Int,
    passInput: String,
    passInputLambda:(String) -> Unit,
    passwordVisible: Boolean,
    passCheckLambda: () -> Unit,
    repeatPassLabelId: Int,
    repeatPassInput: String,
    repeatPassInputLambda: (String) -> Unit,
    repeatPasswordVisible: Boolean,
    repeatPassCheckLambda: ()->Unit
){
    PassInput(textId = passLabelId, passInput = passInput, lambda = passInputLambda, passwordVisible = passwordVisible, passCheckLambda = passCheckLambda)
    Spacer(modifier = Modifier.height(24.dp))
    PassInput(textId = repeatPassLabelId, passInput = repeatPassInput, lambda = repeatPassInputLambda, passwordVisible = repeatPasswordVisible, passCheckLambda = repeatPassCheckLambda)
}

@Composable
fun AppButton(
    lambda: () -> Unit,
    isButtonEnabled: Boolean,
    buttonTextId: Int
){
    Button(
        onClick = lambda,
        shape = RoundedCornerShape(12),
        modifier = Modifier
            .height(46.dp)
            .width(370.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.buttonColor),
            disabledContainerColor = colorResource(id = R.color.disabledButtonColor)
        ),
        enabled = isButtonEnabled
    ) {
        Text(
            text = stringResource(id = buttonTextId),
            fontFamily = robotoFamily,
            fontSize = 16.sp,
            color = colorResource(id = R.color.white)
        )
    }
}

@Composable
fun NewsCard(
    news: News,
//    news: News,
    lambda: () -> Unit

){
    Card(
        modifier = Modifier
            .width(346.dp)
            .height(260.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(0f),
        onClick = lambda
    ){
        val sourcePic = rememberImagePainter(
            data = news.source.sourcePic,
            builder = {
                crossfade(true)
            }
        )
        val newsPic = rememberImagePainter(
            data = news.newsImage,
            builder = {
                crossfade(true)
            }
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = sourcePic,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))
            Column{
                Text(
                    text = "${news.newsTheme}  ${news.source.sourceName}",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(0.dp),
                    lineHeight = 1.sp
                )
                Text(
                    text = news.newsDate,
                    color = Color.Gray,
                    fontSize = 10.sp,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(0.dp),
                    lineHeight = 1.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = news.newsTitle,
                color = Color.Black,
                fontWeight = FontWeight(500),
                fontSize = 14.sp,
                lineHeight = 16.sp,
                modifier = Modifier
                    .width(250.dp)
                    .padding(0.dp)
            )
            Image(
                painter = newsPic,
                contentDescription = null,
                Modifier
                    .width(125.dp)
                    .height(100.dp)
            )
        }
        Text(
            text = news.newsShortenText,
            fontFamily = robotoFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            color = Color.Black,
            lineHeight = 14.sp
        )
    }
}


@Composable
fun NewsUpperRow(
    @PreviewParameter(NewsProvider::class) news: News,


){
    val sourcePic = rememberImagePainter(
        data = news.source.sourcePic,
        builder = {
            crossfade(true)
        }
    )
    Row(
        Modifier
            .fillMaxWidth()
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.back), contentDescription = null)
        Spacer(modifier = Modifier.width(5.dp))
        Image(
            painter = sourcePic,
            contentDescription = null,
            modifier = Modifier.size(30.dp)
            )
        Spacer(modifier = Modifier.width(12.dp))
        Column{
            Text(
                text = "${news.newsTheme}  ${news.source.sourceName}",
                color = Color.Black,
                fontSize = 12.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight(400),
                modifier = Modifier.padding(0.dp),
                lineHeight = 1.sp
            )
            Text(
                text = news.newsDate,
                color = Color.Gray,
                fontSize = 10.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight(400),
                modifier = Modifier.padding(0.dp),
                lineHeight = 1.sp
            )
        }
        Spacer(modifier = Modifier.width(180.dp))
        Image(
            painter = painterResource(id = R.drawable.share),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Image(
            painter = painterResource(id = R.drawable.favs),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}
@Composable
fun NewsHeaderText(
    header: String
){
    Text(
        text = header,
        color = Color.Black,
        fontSize = 18.sp,
        fontFamily = robotoFamily,
        fontWeight = FontWeight(700),
        modifier = Modifier.padding(0.dp),
        lineHeight = 16.sp
    )
}

@Composable
fun NewsText(
    text: String
){
    Text(
        text = text,
        color = Color.Black,
        fontSize = 14.sp,
        fontFamily = robotoFamily,
        fontWeight = FontWeight(400),
        modifier = Modifier.padding(0.dp),
        lineHeight = 14.sp
    )
}

