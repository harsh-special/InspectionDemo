package com.example.core.analytics

interface Analytics {
    fun setScreenAnalytics(className: String?)
    fun screenDestroyedAnalytics(className: String?)
}