package com.example.userfeature.presenter.userinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.userfeature.presenter.userinfo.UserInfoViewModel
import com.example.userfeature.presenter.userinfo.effect.UserInfoEffect
import com.example.userfeature.presenter.userinfo.intent.UserInfoIntent
import com.example.userfeature.presenter.userinfo.state.UserInfoUIState

@Composable
internal fun UserInfoContentScreen(onBackPressed: () -> Unit) {

    val userInfoViewModel = hiltViewModel<UserInfoViewModel>()
    val userInfoState by userInfoViewModel.userInfoState.collectAsStateWithLifecycle()
    val effect by userInfoViewModel.effect.collectAsStateWithLifecycle(null)

    LaunchedEffect(key1 = effect) {
        handleEffect(effect, onBackPressed)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Topbar(
            title = stringResource(R.string.user_detail_screen),
            showBackButton = true,
            onBackPressed = { userInfoViewModel.processIntent(UserInfoIntent.BackPressed.BackPressClicked) })

        when (userInfoState) {
            is UserInfoUIState.Loading -> ShowLoader()
            is UserInfoUIState.ShowContent -> UserInfoScreen(
                (userInfoState as
                        UserInfoUIState.ShowContent).userInfo
            )

            is UserInfoUIState.Error ->
                ErrorView(
                    modifier = Modifier,
                    errorMsg = (userInfoState as UserInfoUIState.Error).errorMessage
                )
        }
    }
}

fun handleEffect(
    effect: UserInfoEffect?,
    onBackPressed: () -> Unit
) {
    if (effect is UserInfoEffect.BackPressEffect.BackPressed) {
        onBackPressed()
    }
}


