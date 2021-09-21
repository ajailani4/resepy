package com.ajailani.resepy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajailani.resepy.data.model.Category
import com.ajailani.resepy.ui.theme.CategoryCardBg
import com.ajailani.resepy.ui.theme.CategoryText
import com.ajailani.resepy.ui.theme.poppinsFontFamily
import com.ajailani.resepy.util.categoryThumbGenerator
import com.ajailani.resepy.util.generateCategory

@Composable
fun CategoryCard(category: Category) {
    Surface(
        shape = RoundedCornerShape(15.dp),
        color = CategoryCardBg,
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
                color = CategoryText,
                fontSize = 16.sp,
                fontFamily = poppinsFontFamily,
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