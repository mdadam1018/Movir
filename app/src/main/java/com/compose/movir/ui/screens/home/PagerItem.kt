package com.compose.movir.ui.screens.home

import android.media.Image
import com.compose.movir.R

data class PagerItem( val id: Int,
                      val title: String,
                      val posterUrl: Int,
                      val duration: String,
                      val genres: String,
                      val rating: Double)


var pagerItem = listOf(
    PagerItem(id = 1,
        title = "Black Adam",
        posterUrl = R.drawable.poster1,
        duration = "3hrs 0 min",
        genres = "Action, Drama",
        rating = 8.2),
    PagerItem(id = 2,
        title = "Varisu",
        posterUrl = R.drawable.poster2,
        duration = "3hrs 12 mins",
        genres = "Action, Drama",
        rating = 8.0),
    PagerItem(id = 3,
        title = "Aquaman",
        posterUrl = R.drawable.poster3,
        duration = "2hrs 23 mins",
        genres = "Action, Drama",
        rating = 7.8),

)
