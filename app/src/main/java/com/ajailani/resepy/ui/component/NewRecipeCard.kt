package com.ajailani.resepy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.ajailani.resepy.R
import com.ajailani.resepy.data.model.Author
import com.ajailani.resepy.data.model.Recipe
import com.ajailani.resepy.ui.theme.BlackGray
import com.ajailani.resepy.ui.theme.DarkGray
import com.ajailani.resepy.util.generateRecipe

@Composable
fun NewRecipeCard(recipe: Recipe) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.size(220.dp, 310.dp),
        elevation = 0.dp
    ) {
        Box {
            Image(
                painter = /*rememberImagePainter(recipe.thumb)*/painterResource(id = R.drawable.recipe_thumb),
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
            ) {
                DifficultySurface(recipe.difficulty)
                ShortInfoSurface(
                    title = recipe.title,
                    time = recipe.time,
                    servings = recipe.servings
                )
            }
        }
    }
}

@Composable
fun DifficultySurface(level: String) {
    Surface(
        shape = CircleShape,
        color = Color.Gray.copy(0.7f),
        modifier = Modifier
            .widthIn(20.dp, 90.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = level,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 10.dp)
        )
    }
}

@Composable
fun ShortInfoSurface(
    title: String,
    time: String,
    servings: String
) {
    Surface(
        color = BlackGray.copy(0.9f),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)) {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "$time | $servings",
                color = Color.Gray,
                fontSize = 14.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewRecipeCard() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {
        NewRecipeCard(generateRecipe())
    }
}