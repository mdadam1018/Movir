package com.compose.movir.ui.screens.main

import androidx.annotation.DrawableRes
import com.compose.movir.R

data class BottomNavItemState(var title: String,
                              @DrawableRes
                              var selectedIcon : Int,
                              var unSelectedIcon : Int,
                              var hasBadge : Boolean )

var bottomNavItems = listOf(
    BottomNavItemState(title = "home",
        selectedIcon = R.drawable.mr_house_fill,
        unSelectedIcon = R.drawable.mr_house,
        hasBadge = false),
    BottomNavItemState(title = "tickets",
        selectedIcon = R.drawable.mr_ticket_fill,
        unSelectedIcon = R.drawable.mr_ticket,
        hasBadge = false),
    BottomNavItemState(title = "search",
        selectedIcon = R.drawable.mr_search_fill,
        unSelectedIcon = R.drawable.mr_search,
        hasBadge = false),
    BottomNavItemState(title = "favorites",
        selectedIcon = R.drawable.mr_heart_fill,
        unSelectedIcon = R.drawable.mr_heart,
        hasBadge = false),
    BottomNavItemState(title = "notification",
        selectedIcon = R.drawable.mr_bell_fill,
        unSelectedIcon = R.drawable.mr_bell,
        hasBadge = true)
    )
