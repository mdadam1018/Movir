package com.compose.movir.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.compose.movir.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
val kanitFamily = FontFamily(
    Font(R.font.kanit_medium,FontWeight.Medium),
    )

val openSansFamily = FontFamily(
    Font(R.font.opensans_regular,FontWeight.Normal),
    Font(R.font.opensans_bold,FontWeight.Bold)
)