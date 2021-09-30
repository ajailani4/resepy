package com.ajailani.resepy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.ajailani.resepy.data.model.response.RecipeResponse
import com.ajailani.resepy.ui.theme.poppinsFontFamily
import com.ajailani.resepy.util.generateRecipeResponse

@Composable
fun RecipeCard(
    recipeResponse: RecipeResponse?,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(15.dp),
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Surface(shape = RoundedCornerShape(10.dp)) {
                Image(
                    painter = rememberImagePainter(recipeResponse?.thumb),
                    contentDescription = recipeResponse?.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(85.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                if (recipeResponse != null) {
                    Text(
                        text = recipeResponse.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "${recipeResponse?.times} | ${recipeResponse?.servings}",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xF6F6F6
)
@Composable
fun PreviewRecipeCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        RecipeCard(
            recipeResponse = generateRecipeResponse(),
            onClick = {}
        )
    }
}