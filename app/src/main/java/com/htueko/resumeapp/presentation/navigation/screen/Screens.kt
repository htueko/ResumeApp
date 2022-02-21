// ktlint-disable filename
// https://stackoverflow.com/questions/57395287/how-to-disable-filename-in-ktlint
// to suppress matching name test for detekt
@file:Suppress("MatchingDeclarationName")

package com.htueko.resumeapp.presentation.navigation.screen

/**
 * navigation routes for entire app.
 */
sealed class Screen(val route: String) {
    @Suppress("MatchingDeclarationName")
    object DashboardScreen : Screen("dashboard")
    object DetailScreen : Screen("detail")
    object AddEducationScreen : Screen("add_education")
    object AddProjectScreen : Screen("add_project")
    object AddResumeScreen : Screen("add_resume")
    object AddSkillScreen : Screen("add_skill")
    object AddWorkScreen : Screen("add_work")
}
