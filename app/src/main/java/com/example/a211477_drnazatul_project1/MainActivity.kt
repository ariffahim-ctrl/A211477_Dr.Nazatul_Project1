package com.example.a211477_drnazatul_project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.a211477_drnazatul_project1.screens.ProfileScreen
import com.example.a211477_drnazatul_project1.ui.theme.UpSkillTheme
import com.example.a211477_drnazatul_project1.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpSkillTheme {
                val navController = rememberNavController()
                val viewModel: UpSkillViewModel = viewModel()
                val uiState by viewModel.uiState.collectAsState()

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(
                            uiState = uiState,
                            onNavigateToGoal = { navController.navigate("goal") },
                            onEnrollClick = { course ->
                                viewModel.enrollInCourse(course)
                                navController.navigate("details")
                            },
                            onNavigateToProfile = { navController.navigate("profile") },
                            onNavigateToSearch = { navController.navigate("search") }
                        )
                    }
                    composable("goal") {
                        GoalScreen(uiState.dailyGoal) {
                            viewModel.updateGoal(it)
                            navController.popBackStack()
                        }
                    }
                    composable("details") {
                        DetailScreen(uiState.enrolledCourse) { navController.popBackStack() }
                    }
                    composable("profile") {
                        ProfileScreen(
                            uiState = uiState,
                            onSaveProfile = { newName, newEmail ->
                                viewModel.updateProfile(newName, newEmail)
                                // No popBackStack here because we want to stay on the profile
                                // page to see the changes, isEditing = false handles the UI switch
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("search") {
                        SearchScreen(
                            onCourseClick = { course ->
                                viewModel.enrollInCourse(course) // Reuse your enroll logic
                                navController.navigate("details")
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}