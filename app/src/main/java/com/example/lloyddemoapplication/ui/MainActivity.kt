package com.example.lloyddemoapplication.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.core.analytics.Analytics
import com.example.core.analytics.AnalyticsImpl
import com.example.userfeature.presenter.UserFlowNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Analytics by AnalyticsImpl() {

    private val tag: String = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UserFlowNavigation()
        }
    }

    override fun onStart() {
        super.onStart()
        setScreenAnalytics(tag)
    }

    override fun onDestroy() {
        super.onDestroy()
        screenDestroyedAnalytics(tag)
    }
}