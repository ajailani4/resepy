package com.ajailani.resepy.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowIosBack

@Composable
fun NewRecipesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Resep Terbaru",
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {  }
                    ) {
                        Icon(
                            imageVector = EvaIcons.Fill.ArrowIosBack,
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp
            )
        }
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewRecipesScreen() {
    NewRecipesScreen()
}