package com.compose.movir.ui.screens.main


import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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

