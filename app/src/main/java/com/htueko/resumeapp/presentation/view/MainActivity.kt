package com.htueko.resumeapp.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.htueko.resumeapp.presentation.navigation.ResumeNavHost
import com.htueko.resumeapp.presentation.navigation.Screen
import com.htueko.resumeapp.presentation.theme.ResumeAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResumeAppTheme {
                val navController = rememberNavController()
                ResumeNavHost(navHostController = navController)
            }
        }
    }
}
