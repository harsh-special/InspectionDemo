package com.example.userfeature.presenter.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.core.analytics.Analytics
import com.example.core.analytics.AnalyticsImpl
import com.example.domain.model.Questions
import com.example.userfeature.R
import com.example.userfeature.presenter.ui.inspection.InspectionFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), Analytics by AnalyticsImpl() {

    private lateinit var navController: NavController
    private val tag: String = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.home_activity)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun navigateToInspectionScreen() {
        navController.navigate(R.id.action_loginFragment_to_inspectionFragment)
    }

    fun navigateToStartInspectionScreen(questions: Questions?) {
        val action =
            InspectionFragmentDirections.actionInspectionFragmentToInspectionStartFragment(questions)
        navController.navigate(action)
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