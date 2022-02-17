package com.htueko.resumeapp.presentation.navigation

/**
 * navigation routes for entire app.
 */
sealed class Screen(val route: String){
    object DashboardScreen: Screen("dashboard")
    object DetailScreen: Screen("detail")
    object AddEducationScreen: Screen("add_education")
    object AddProjectScreen: Screen("add_project")
    object AddResumeScreen: Screen("add_resume")
    object AddSkillScreen: Screen("add_skill")
    object AddWorkScreen: Screen("add_work")
}