package com.example.userfeature.presenter.user.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.model.User
import com.example.ui.LocalElevation
import com.example.ui.LocalPadding
import com.example.ui.LocalSpacing
import com.example.ui.Padding
import com.example.ui.Spacing

@Composable
internal fun UserItem(user: User, onItemClick: () -> Unit) {
    val padding = LocalPadding.current
    val elevation = LocalElevation.current
    val spacing = LocalSpacing.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.small)
            .clickable { onItemClick() },
        elevation = elevation.small,
    ) {
        UserItemContent(user = user, padding = padding, spacing = spacing)
    }
}

@Composable
private fun UserItemContent(user: User, padding: Padding, spacing: Spacing) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.medium)
    ) {
        UserTextColumn(user = user, spacing = spacing)
    }
}

@Composable
private fun UserTextColumn(user: User, spacing: Spacing) {
    Column {
        Text(
            text = user.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = spacing.small)
        )
        Text(
            text = user.email,
            style = MaterialTheme.typography.body1
        )
    }
}
