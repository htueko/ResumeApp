package com.htueko.resumeapp.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.htueko.resumeapp.presentation.view.addeducation.ui.AddEducationScreen
import com.htueko.resumeapp.presentation.view.addproject.ui.AddProjectScreen
import com.htueko.resumeapp.presentation.view.addresume.ui.AddResumeScreen
import com.htueko.resumeapp.presentation.view.addskill.ui.AddSkillScreen
import com.htueko.resumeapp.presentation.view.addwork.ui.AddWorkScreen
import com.htueko.resumeapp.presentation.view.detail.ui.DetailScreen
import com.htueko.resumeapp.presentation.view.main.ui.MainScreen

@ExperimentalMaterialApi
@Composable
fun ResumeNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.DashboardScreen.route
    ) {
        // main screen
        composable(
            route = Screen.DashboardScreen.route
        ) {
            MainScreen(
                onAddResumeClick = {
                    navHostController.navigate(Screen.AddResumeScreen.route + "/-1")
                },
                onResumeClick = { resumeId ->
                    navHostController.navigate(Screen.DetailScreen.route + "/$resumeId")
                })
        }
        // detail screen
        composable(
            route = Screen.DetailScreen.route + "/{resumeId}",
            arguments = listOf(
                navArgument("resumeId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            val resumeId: Int? = navBackStackEntry.arguments?.getInt("resumeId")
            DetailScreen(
                onEditResumeClick = {
                    navHostController.navigate(Screen.AddResumeScreen.route + "/$resumeId")
                },
                onEditEducationClick = {
                    navHostController.navigate(Screen.AddEducationScreen.route + "/$resumeId")
                },
                onAddEducationClick = {
                    navHostController.navigate(Screen.AddEducationScreen.route + "/$resumeId")
                },
                onEditProjectClick = {
                    navHostController.navigate(Screen.AddProjectScreen.route + "/$resumeId")
                },
                onAddProjectClick = {
                    navHostController.navigate(Screen.AddProjectScreen.route + "/$resumeId")
                },
                onEditSkillClick = {
                    navHostController.navigate(Screen.AddSkillScreen.route + "/$resumeId")
                },
                onAddSkillClick = {
                    navHostController.navigate(Screen.AddSkillScreen.route + "/$resumeId")
                },
                onEditWorkClick = {
                    navHostController.navigate(Screen.AddWorkScreen.route + "/$resumeId")
                },
                onAddWorkClick = {
                    navHostController.navigate(Screen.AddWorkScreen.route + "/$resumeId")
                }
            )
        }
        // add resume screen
        composable(
            route = Screen.AddResumeScreen.route + "/{resumeId}",
            arguments = listOf(
                navArgument("resumeId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            val resumeId: Int? = navBackStackEntry.arguments?.getInt("resumeId")
            AddResumeScreen(
                onSaveResumeClick = {
                    navHostController.navigate(Screen.DetailScreen.route + "/$it") {
                        popUpTo(Screen.DetailScreen.route + "/$it") {
                            inclusive = true
                        }
                    }
                })
        }
        // add eduction screen
        composable(
            route = Screen.AddEducationScreen.route + "/{resumeId}",
            arguments = listOf(
                navArgument("resumeId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            val resumeId: Int? = navBackStackEntry.arguments?.getInt("resumeId")
            AddEducationScreen(
                navController = navHostController,
                resumeId = resumeId
            )
        }
        // add project screen
        composable(
            route = Screen.AddProjectScreen.route + "/{resumeId}",
            arguments = listOf(
                navArgument("resumeId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            val resumeId: Int? = navBackStackEntry.arguments?.getInt("resumeId")
            AddProjectScreen(
                navController = navHostController,
                resumeId = resumeId
            )
        }
        // add skill screen
        composable(
            route = Screen.AddSkillScreen.route + "/{resumeId}",
            arguments = listOf(
                navArgument("resumeId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            val resumeId: Int? = navBackStackEntry.arguments?.getInt("resumeId")
            AddSkillScreen(
                resumeId = resumeId,
                onSaveSkillClick = {
                    // navigate back to detail screen with newly updated resumeId
                    // remove AddSkillScreen from backstack
//                    navHostController.navigate(Screen.DetailScreen.route + "/$it") {
//                        popUpTo(Screen.AddSkillScreen.route) {
//                            inclusive = true
//                        }
//                    }
                    navHostController.popBackStack(
                        Screen.DetailScreen.route + "/$it",
                        inclusive = true
                    )
                }
            )
        }
        // add work screen
        composable(
            route = Screen.AddWorkScreen.route + "/{resumeId}",
            arguments = listOf(
                navArgument("resumeId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            val resumeId: Int? = navBackStackEntry.arguments?.getInt("resumeId")
            AddWorkScreen(
                navController = navHostController,
                resumeId = resumeId
            )
        }

    }

}