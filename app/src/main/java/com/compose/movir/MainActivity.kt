package com.compose.movir


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.graphics.toArgb
import com.compose.movir.ui.screens.home.HomeViewModel
import com.compose.movir.ui.screens.main.MainScreen
import com.compose.movir.ui.theme.MovirTheme
import com.compose.movir.ui.theme.text_black
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovirTheme {
                window.statusBarColor = text_black.toArgb()
                MainScreen(homeViewModel = homeViewModel)
            }
        }
    }
}

