package com.compose.movir.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.movir.R

import androidx.compose.runtime.getValue

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale

val image1 = R.drawable.poster1
val image2 = R.drawable.poster2

var imagelist = listOf (image1, image2)


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithScale() {
    val pagerState = rememberPagerState(pageCount = { imagelist.size})


    Row(Modifier.fillMaxSize()) {
        HorizontalPager(
            pageSize = PageSize.Fixed(200.dp),
            state = pagerState,
            pageSpacing = 10.dp,
            contentPadding = PaddingValues(horizontal = 22.dp)
        ) { page ->
                val scale = if (page == pagerState.currentPage) 1f else 0.7f
                Image(
                    modifier = Modifier
                        .height(600.dp)
                        .width(340.dp)
                        .scale(scale),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Image $page",
                    painter = painterResource(id = imagelist[page])
                )

        }
    }
}
@Preview
@Composable
fun HorizontalPagerWithScalePreview() {
    HorizontalPagerWithScale()
}

