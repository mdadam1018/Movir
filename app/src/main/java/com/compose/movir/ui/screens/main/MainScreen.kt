package com.compose.movir.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.compose.movir.ui.screens.home.HomeScreen
import com.compose.movir.ui.screens.home.HomeViewModel


@Composable
fun MainScreen(homeViewModel : HomeViewModel = hiltViewModel()) {
    Scaffold(bottomBar = {
        BottomAppBar(containerColor = Color.Transparent) {
            BottomNavBar()
        } },
        containerColor = Color.Black
    ) {

        HomeScreen(viewModel = homeViewModel)

    }

}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Composable
fun alternateScreen() {
    Column (modifier = Modifier.fillMaxSize()){
        Text("Hello")
    }
}