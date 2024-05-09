
package com.compose.movir.ui.screens.home

import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.movir.R
import com.compose.movir.ui.theme.Gold_Bright
import com.compose.movir.ui.theme.Gold_Dark
import com.compose.movir.ui.theme.kanitFamily
import com.compose.movir.ui.theme.openSansFamily
import com.compose.movir.ui.theme.text_black
import com.compose.movir.ui.theme.text_gray
import com.compose.movir.ui.theme.text_gray_dark
import com.compose.movir.ui.theme.text_white
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.cloudy.Cloudy
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild


@OptIn(ExperimentalPagerApi::class, )
@Composable
fun MoviePager( item: PagerItem) {

    Box(modifier = Modifier) {
        val hazeState = remember { HazeState() }
        Box(modifier = Modifier
           ) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .aspectRatio(9/16f)
                    .padding(bottom = 50.dp)
                    .haze(
                        state = hazeState,
                        style = HazeDefaults.style(tint = Color.Black.copy(alpha = 0.1f), blurRadius = 8.dp),
                    )
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(32.dp)
            ) {
                Image(
                    painter = painterResource(id = item.posterUrl),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.BottomCenter)
                    .hazeChild(
                        state = hazeState,
                        style = HazeStyle( blurRadius = 50.dp),
                        shape = RoundedCornerShape(16.dp),
                    ),
            ) {
                Column(Modifier.padding(vertical = 15.dp, horizontal = 15.dp)) {
                    Text(
                        text = "Black Adam",
                        fontFamily = kanitFamily,
                        fontSize = 32.sp,
                        color = text_white
                    )
                    Text(
                        text = "Black Adam",
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = text_gray,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    Text(
                        text = "Black Adam",
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = text_gray
                    )


                    Row(
                        modifier = Modifier.padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.weight(0.5f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.mr_star),
                                contentDescription = null,
                                tint = Gold_Bright
                            )
                            Text(
                                text = "5.8/10",
                                fontFamily = kanitFamily,
                                fontSize = 18.sp,
                                color = text_white,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(Gold_Bright),
                            modifier = Modifier.weight(0.5f)
                        ) {
                            Text(
                                text = "More Details",
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = text_black
                            )

                        }
                    }
                }
            }

            }

    }

}


@Preview
@Composable
fun MoviePagerPreview() {
    MoviePager(item = PagerItem(id = 1,
        title = "black adam",
        posterUrl = R.drawable.poster1,
        duration = "300",
        genres = "acion,drama",
        rating = 8.2), )
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun MoviePagerPreview2() {
    Box(modifier = Modifier.fillMaxSize()){
        HorizontalPager(count = 2, modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)) {
            MoviePager(item = PagerItem(id = 1,
                title = "black adam",
                posterUrl = R.drawable.poster1,
                duration = "300",
                genres = "acion,drama",
                rating = 8.2), )
        }
    }
}
@Composable
fun GradientButton(
    text : String,
    gradient : Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .then(modifier)
                .wrapContentSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text,
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = text_black)
        }
    }
}
