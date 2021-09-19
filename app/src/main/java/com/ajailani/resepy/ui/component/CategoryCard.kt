package com.ajailani.resepy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajailani.resepy.R
import com.ajailani.resepy.data.model.Category
import com.ajailani.resepy.ui.theme.BackgroundGray
import com.ajailani.resepy.util.categoryThumbGenerator
import com.ajailani.resepy.util.generateCategory
import com.ajailani.resepy.util.generateRecipe

@Composable
fun CategoryCard(category: Category) {
    Surface(
        shape = RoundedCornerShape(15.dp),
        color = BackgroundGray,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Surface(shape = RoundedCornerShape(10.dp)) {
                Image(
                    painter = painterResource(id = categoryThumbGenerator(category.key)),
                    contentDescription = category.category,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(70.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = category.category,
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryCard() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {
        CategoryCard(generateCategory())
    }
}