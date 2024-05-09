package com.compose.movir.ui.screens.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.compose.movir.R
import com.compose.movir.ui.theme.bg_black_dark
import com.compose.movir.ui.theme.kanitFamily
import com.compose.movir.ui.theme.mid_black
import com.compose.movir.ui.theme.openSansFamily


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.bg_png2),
                contentScale = ContentScale.FillBounds
            )
        ){
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = "Welcome, Ryder",
                        fontFamily = kanitFamily,
                        fontSize = 24.sp,
                        color = Color.White
                    )


                    Card(
                        modifier = Modifier
                            .size(60.dp)
                            .border(
                                width = 3.dp,
                                shape = CircleShape,
                                color = Color.White
                            ),
                        shape = CircleShape,
                    ) {

                        AsyncImage(
                            model = R.drawable.ryder,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Text(text = "Take a break and watch a movie.",
                    fontFamily = kanitFamily,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 20.dp))

                var selectedTabIndex by remember {
                    mutableIntStateOf(0)
                }

                val tabItems = listOf("Trending", "New", "Fantasy", "Sci-fi", "Action", "Adventure", "Bollywood", "Romantic")
                CustomTabRow(selectedTabIndex = selectedTabIndex, onTabSelected = {
                    selectedTabIndex = it
                }, tabItems = tabItems,
                    modifier = Modifier.padding(vertical = 20.dp),
                )

                //pager
                var pageitems = pagerItem
                val pageState = rememberPagerState(pageCount = {pageitems.size})
                HorizontalPager(
                    pageSize = PageSize.Fixed(350.dp),
                    beyondBoundsPageCount = 2,
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    pageSpacing = 1.dp,
                    state = pageState,
                    modifier = Modifier
                        .height(600.dp)
                        .padding(top = 20.dp, end = 10.dp)
                ) { index ->
                    MoviePager(item = pageitems[index] )
                }

                //indicator
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(vertical = 5.dp)
                        .align(Alignment.CenterHorizontally)

                ) {
                    Indicators(size = pageitems.size, index = pageState.currentPage)
                }


            }
        }


    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun CustomTabRow(selectedTabIndex: Int,
                 onTabSelected: (Int) -> Unit,
                 tabItems : List<String>,
                 modifier : Modifier = Modifier,
                 ) {

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            divider = {},
            indicator = {},
            edgePadding = 0.dp,
            modifier = modifier
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(selected = selectedTabIndex == index,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Gray,
                    onClick = {
                        onTabSelected(index)
                    }) {
                    Text(
                        text = item,
                        fontFamily = openSansFamily,
                        fontSize = 16.sp,
                    )
                }
            }
        }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = tween(
            durationMillis = 200, // Adjust duration as needed
            easing = LinearEasing // Use LinearEasing for smooth ease
        ), label = ""
    )

    Box(
        modifier = Modifier
            .height(5.dp)
            .width(width.value)
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = if (isSelected) Color.White else mid_black
            )
    ) {

    }
}

