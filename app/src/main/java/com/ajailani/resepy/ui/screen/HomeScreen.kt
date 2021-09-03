package com.ajailani.resepy.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajailani.resepy.ui.component.fontFamily
import com.ajailani.resepy.ui.theme.BackgroundGray
import com.ajailani.resepy.ui.theme.DarkGray
import com.ajailani.resepy.ui.theme.Primary
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Search

@Composable
fun HomeScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {
        Header()
        SearchTextField()
    }
}

@Composable
fun Header() {
    Column(modifier = Modifier.padding(bottom = 20.dp)) {
        Text(
            text = "Hello",
            style = TextStyle(
                color = Primary,
                fontSize = 20.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
        )

        Text(
            text = "What you want to cook today?",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 15.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
fun SearchTextField() {
    var text by remember { mutableStateOf("") }

    Box(contentAlignment = Alignment.CenterStart) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = EvaIcons.Fill.Search,
                    contentDescription = "Search Icon",
                    tint = DarkGray
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BackgroundGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                color = Color.Black,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            ),
            placeholder = {
                Text(
                    text = "Search Recipes",
                    style = TextStyle(
                        color = DarkGray,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}