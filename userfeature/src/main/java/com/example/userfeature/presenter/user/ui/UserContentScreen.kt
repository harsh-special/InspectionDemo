package com.example.userfeature.presenter.user.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.ErrorView
import com.example.ui.ShowLoader
import com.example.ui.Topbar
import com.example.userfeature.R
import com.example.userfeature.presenter.user.UserViewModel
import com.example.userfeature.presenter.user.effect.UserEffect
import com.example.userfeature.presenter.user.intent.UserIntent
import com.example.userfeature.presenter.user.state.UserUIState

@Composable
internal fun UserContentScreen(navigateToUserInfoScreen: (id: String?) -> Unit) {
    val userViewModel: UserViewModel = hiltViewModel()
    val viewState by userViewModel.userState.collectAsStateWithLifecycle()
    val effect by userViewModel.effect.collectAsStateWithLifecycle(null)

    LaunchedEffect(key1 = effect) {
        handleEffect(effect, navigateToUserInfoScreen)
    }

    Column {
        Topbar(title = stringResource(R.string.home_screen_title))
        when (viewState) {
            is UserUIState.Loading -> {
                ShowLoader()
            }

            is UserUIState.ShowContent -> {
                val users = (viewState as UserUIState.ShowContent).users
                UserList(users) { user ->
                    userViewModel.processIntent(UserIntent.UIIntent.ListItemClicked(user))
                }
            }

            is UserUIState.Error -> {
                ErrorView(
                    modifier = Modifier,
                    errorMsg = (viewState as UserUIState.Error).errorMessage
                )
            }
        }
    }
}

private fun handleEffect(
    effect: UserEffect?,
    navigateToUserInfoScreen: (id: String?) -> Unit
) {
    if (effect is UserEffect.NavigationEffect.NavigateUserInfoScreen) {
        navigateToUserInfoScreen(effect.userId)
    }
}