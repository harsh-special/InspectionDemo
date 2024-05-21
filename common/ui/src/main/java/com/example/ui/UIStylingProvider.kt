package com.example.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Spacing(val small: Dp = 4.dp, val medium: Dp = 8.dp, val large: Dp = 16.dp)
data class TextSizes(
    val small: TextUnit = 12.sp,
    val medium: TextUnit = 16.sp,
    val large: TextUnit = 20.sp,
    val extraLarge: TextUnit = 22.sp,
    val doubleExtraLarge: TextUnit = 24.sp
)

data class Elevation(val small: Dp = 4.dp, val medium: Dp = 8.dp, val large: Dp = 16.dp)
data class Padding(
    val small: PaddingValues = PaddingValues(8.dp),
    val medium: PaddingValues = PaddingValues(16.dp),
    val large: PaddingValues = PaddingValues(24.dp)
)

val LocalElevation = compositionLocalOf { Elevation() }
val LocalPadding = compositionLocalOf { Padding() }
val LocalTextSizes = compositionLocalOf { TextSizes() }
val LocalSpacing = compositionLocalOf { Spacing() }

@Composable
private inline fun <reified T> ProvideValues(
    value: T,
    crossinline content: @Composable (T) -> Unit
) {
    val local = remember(value) {
        when (T::class) {
            Elevation::class -> LocalElevation as ProvidedValue<T>
            Padding::class -> LocalPadding as ProvidedValue<T>
            TextSizes::class -> LocalTextSizes as ProvidedValue<T>
            Spacing::class -> LocalSpacing as ProvidedValue<T>
            else -> error("Unsupported type: ${T::class.java.simpleName}")
        }
    }

    CompositionLocalProvider(local) {
        content(value)
    }
}