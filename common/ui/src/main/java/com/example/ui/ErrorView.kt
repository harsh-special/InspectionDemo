package com.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    errorMsg: String?
) {
    val padding = LocalPadding.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding.large),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = errorMsg ?: stringResource(R.string.default_error)
        )
    }
}
