package com.compose.movir.ui.screens.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.compose.movir.R
import com.compose.movir.data.models.MovieResult
import com.compose.movir.ui.theme.Gold_Bright
import com.compose.movir.ui.theme.kanitFamily
import com.compose.movir.ui.theme.openSansFamily
import com.compose.movir.ui.theme.text_black
import com.compose.movir.ui.theme.text_gray
import com.compose.movir.ui.theme.text_white
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild


@Composable
fun MoviePager(item: MovieResult) {

    Box(modifier = Modifier) {
        val hazeState = remember { HazeState() }
        Box(
            modifier = Modifier
        ) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(bottom = 50.dp)
                    .haze(
                        state = hazeState,
                        style = HazeDefaults.style(
                            tint = Color.Black.copy(alpha = 0.5f), blurRadius = 8.dp
                        ),
                    )
                    .align(Alignment.TopCenter), shape = RoundedCornerShape(32.dp)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${item.poster_path}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(180.dp)
                    .align(Alignment.BottomCenter)
                    .hazeChild(
                        state = hazeState,
                        style = HazeStyle(blurRadius = 50.dp),
                        shape = RoundedCornerShape(16.dp),
                    ),
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .height(10.dp)
                        .padding(vertical = 15.dp, horizontal = 15.dp)
                ) {
                    Text(
                        text = "${item.title}",
                        fontFamily = kanitFamily,
                        fontSize = 30.sp,
                        lineHeight = 32.sp,
                        color = text_white,
                    )
                    Text(
                        text = "${item.genres.joinToString(", ")}",
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = text_gray,
                        modifier = Modifier.padding(vertical = 0.dp)
                    )
                    Text(
                        text = "${item.release_date}",
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = text_gray,
                    )

                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                        .align(Alignment.BottomCenter),
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
                        var rating = formatNumber(item.vote_average)
                        Text(
                            text = "${rating}/10",
                            fontFamily = kanitFamily,
                            fontSize = 18.sp,
                            color = text_white,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(Gold_Bright),
                        modifier = Modifier.weight(0.7f)
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


@Preview
@Composable
fun MoviePagerPreview() {
    MoviePager(
        item = MovieResult(
            adult = false,
            backdrop_path = "/123.jpg",
            genre_ids = listOf(),
            id = 1,
            original_language = "en",
            original_title = "Black Adam",
            overview = "Black Adam",
            popularity = 1.0,
            poster_path = "/123.jpg",
            release_date = "2022-12-12",
            title = "Black Adam",
            video = false,
            vote_average = 1.0,
            vote_count = 1
        )
    )
}

@Preview
@Composable
fun MoviePagerPreview2() {

}

fun formatNumber(value: Double): String {
    val formattedValue = "%.1f".format(value)  // Round to one decimal place
    val withoutTrailingZeros = formattedValue.replace(Regex("\\.?0*$"), "")
    return withoutTrailingZeros
}