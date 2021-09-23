package com.ajailani.resepy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ajailani.resepy.ui.Navigation
import com.ajailani.resepy.ui.event.HomeEvent
import com.ajailani.resepy.ui.screen.HomeScreen
import com.ajailani.resepy.ui.theme.ResepMakananTheme
import com.ajailani.resepy.ui.viewmodel.HomeViewModel
import com.ajailani.resepy.ui.viewmodel.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                Content()
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    ResepMakananTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun Content() {
    Navigation(rememberNavController())
}