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
            MainScreen(navHostController)
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
                navController = navHostController,
                resumeId = resumeId
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
                navController = navHostController,
                resumeId = resumeId
            )
        }
        // add eduction screen
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
            AddEducationScreen(
                navController = navHostController,
                resumeId = resumeId
            )
        }
        // add project screen
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
            AddProjectScreen(
                navController = navHostController,
                resumeId = resumeId
            )
        }
        // add skill screen
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
            AddSkillScreen(
                navController = navHostController,
                resumeId = resumeId
            )
        }
        // add work screen
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
            AddWorkScreen(
                navController = navHostController,
                resumeId = resumeId
            )
        }

    }

}