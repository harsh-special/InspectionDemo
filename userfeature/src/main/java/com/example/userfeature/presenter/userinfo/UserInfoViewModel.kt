package com.example.userfeature.presenter.userinfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.Response
import com.example.core.base.BaseViewModel
import com.example.domain.model.UserInfo
import com.example.domain.usecase.userinfo.GetUserInfoUseCase
import com.example.userfeature.presenter.userinfo.effect.UserInfoEffect
import com.example.userfeature.presenter.userinfo.intent.UserInfoIntent
import com.example.userfeature.presenter.userinfo.state.UserInfoUIState
import com.example.userfeature.presenter.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UserInfoViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<UserInfoIntent, UserInfoEffect>() {

    private val _userInfoState = MutableStateFlow<UserInfoUIState>(UserInfoUIState.Loading(false))
    val userInfoState: StateFlow<UserInfoUIState> = _userInfoState

    init {
        processIntent(UserInfoIntent.FetchUserInfo)
    }

    override fun processIntent(intent: UserInfoIntent) {
        when (intent) {
            is UserInfoIntent.FetchUserInfo -> fetchUserInfo(savedStateHandle[Constants.userId])
            is UserInfoIntent.BackPressed.BackPressClicked -> navigateBack()
        }
    }

    private fun fetchUserInfo(userId: String?) {
        updateLoadingState(true)
        viewModelScope.launch {
            updateLoadingState(false)
            handleUserInfoResult(getUserInfoUseCase(userId))
        }
    }

    private fun updateLoadingState(loadingState: Boolean) {
        _userInfoState.value = UserInfoUIState.Loading(loadingState)
    }

    private fun handleUserInfoResult(response: Response<UserInfo>) {
        _userInfoState.value = when (response) {
            is Response.Success -> UserInfoUIState.ShowContent(response.data)
            is Response.Error -> UserInfoUIState.Error(response.exception.localizedMessage)
            is Response.Loading -> UserInfoUIState.Loading(response.showLoading)
        }
    }

    private fun navigateBack() {
        setEffect {
            UserInfoEffect.BackPressEffect.BackPressed
        }
    }
}
