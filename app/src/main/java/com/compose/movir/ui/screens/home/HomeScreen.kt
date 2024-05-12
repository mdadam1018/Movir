package com.compose.movir.ui.screens.home


import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.compose.movir.R
import com.compose.movir.ui.theme.kanitFamily
import com.compose.movir.ui.theme.mid_black
import com.compose.movir.ui.theme.openSansFamily
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import com.compose.movir.data.models.MovieResult
import com.compose.movir.ui.screens.ShimmerItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel : HomeViewModel= hiltViewModel()) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .background(Color.Black)


    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .paint(

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
                            contentScale = ContentScale.Fit,

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


                //movie tabs
                val tabItems = listOf("Trending", "New", "Fantasy", "Sci-fi", "Action", "Adventure", "Bollywood", "Romantic")
                CustomTabRow(selectedTabIndex = selectedTabIndex, onTabSelected = {
                    selectedTabIndex = it
                }, tabItems = tabItems,
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 5.dp),
                )

                //pager

                val currentMovies by viewModel.moviesResult.collectAsState(initial = emptyList())

                var movieItems = listOf<MovieResult>()

                if (currentMovies.isEmpty()) {
                    movieItems = emptyList()
                } else {
                    movieItems = currentMovies.subList(0, 4) // Assuming you still want the first 4 elements
                }

                val isLoading by viewModel.isLoading.collectAsState(initial = false)
                Log.d("movie", "HomeScreen: $movieItems")
                Log.d("TAG", "HomeScreen: $isLoading")


                val coroutineScope = rememberCoroutineScope()
                val pageState = rememberPagerState(pageCount = {movieItems.size})
                HorizontalPager(
                    pageSize = PageSize.Fixed(320.dp),
                    beyondBoundsPageCount = 2,
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    pageSpacing = 1.dp,
                    state = pageState,
                    modifier = Modifier
                        .padding(top = 10.dp, end = 10.dp, start = 10.dp)
                ) { index ->
                    LaunchedEffect(Unit) {
                        while (true) {
                            delay(2000)
                            coroutineScope.launch {
                                pageState.animateScrollToPage((pageState.currentPage + 1) % movieItems.size)
                            }
                        }
                    }
                  ShimmerItem(isLoading = isLoading, contentAfterLoading = {MoviePager(item = movieItems[index]) })
                }

                //indicator
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .align(Alignment.CenterHorizontally)

                ) {
                    Indicators(size = movieItems.size, index = pageState.currentPage)
                }


            }
        }


    }
}

@Preview
@Composable
fun HomeScreenPreview() {

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
            durationMillis = 200,
            easing = LinearEasing
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

