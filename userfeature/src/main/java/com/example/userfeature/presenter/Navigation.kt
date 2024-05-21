package com.example.userfeature.presenter

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core.Route
import com.example.userfeature.presenter.user.ui.UserContentScreen
import com.example.userfeature.presenter.userinfo.ui.UserInfoContentScreen
import com.example.userfeature.presenter.utils.Constants

@Composable
fun UserFlowNavigation() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
            composable(Route.HomeScreen.route) {
                UserContentScreen {
                    navController.navigate(
                        Route.UserInfoScreen.route.plus("/")
                            .plus(it)
                    )
                }
            }
            composable(
                Route.UserInfoScreen.route.plus("/{${Constants.userId}}"),
                arguments = listOf(navArgument(Constants.userId) { type = NavType.StringType })
            ) {
                UserInfoContentScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}