package com.example.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun Topbar(title: String, showBackButton: Boolean = false, onBackPressed: () -> Unit = { }) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon =
        if (showBackButton) {
            {
                // You can add a back button or navigation icon here
                IconButton(onClick = {
                    onBackPressed()
                }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.content_description_back_text)
                    )
                }
            }
        } else null,
        backgroundColor = MaterialTheme.colors.primary
    )
}