package com.ajailani.resepy.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajailani.resepy.data.model.Author
import com.ajailani.resepy.ui.theme.BlackGray
import com.ajailani.resepy.ui.theme.DarkGray
import com.ajailani.resepy.ui.theme.poppinsFontFamily
import com.ajailani.resepy.util.generateAuthor

@Composable
fun AuthorCard(author: Author) {
    Surface(
        color = BlackGray.copy(0.9f),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)) {
            Text(
                text = "Resep oleh:",
                color = Color.Gray,
                fontSize = 13.sp,
                fontFamily = poppinsFontFamily
            )
            Text(
                text = author.user,
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = poppinsFontFamily
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = author.datePublished,
                color = DarkGray,
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAuthorCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        AuthorCard(generateAuthor())
    }
}