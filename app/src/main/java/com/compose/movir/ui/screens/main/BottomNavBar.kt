package com.compose.movir.ui.screens.main


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.movir.ui.theme.Gold_Bright
import com.compose.movir.ui.theme.MovirTheme
import com.compose.movir.ui.theme.text_black


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavBar() {
        val navItems = bottomNavItems
        var selectedItem by rememberSaveable { mutableStateOf(0) }

        Scaffold(
            containerColor = Color.Transparent,
            content = {
                NavigationBar( containerColor = text_black, modifier = Modifier
                    .clip(RoundedCornerShape(100))) {
                    Spacer(modifier = Modifier.weight(0.1f))
                    navItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = {
                                    BadgedBox(badge = {
                                        if (item.hasBadge) Badge(containerColor = Gold_Bright)

                                    })  {
                                        Icon(
                                            painter = painterResource(id = if (selectedItem == index) item.selectedIcon else item.unSelectedIcon),
                                            contentDescription = null,
                                        )
                                    }},
                                selected = selectedItem == index,
                                onClick = {
                                    selectedItem = index

                                },
                                colors = NavigationBarItemColors(
                                    selectedIconColor = Color.White,
                                    unselectedIconColor = Color.White,
                                    selectedIndicatorColor = Transparent,
                                    selectedTextColor = Color.White,
                                    unselectedTextColor = Color.White,
                                    disabledIconColor = Color.White,
                                    disabledTextColor = Color.White
                                ) )



                    }
                    Spacer(modifier = Modifier.weight(0.1f))
                }
            }
        )



}

@Preview
@Composable
fun BottomNavBarPreview() {

    BottomNavBar()
}



