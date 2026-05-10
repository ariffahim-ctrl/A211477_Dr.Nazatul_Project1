package com.example.a211477_drnazatul_project1

import androidx.lifecycle.ViewModel
import com.example.a211477_drnazatul_project1.data.UserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UpSkillViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun updateGoal(newGoal: String) {
        _uiState.update { it.copy(dailyGoal = newGoal) }
    }

    fun enrollInCourse(courseName: String) {
        _uiState.update { it.copy(enrolledCourse = courseName) }
    }

    fun updateProfile(newName: String, newEmail: String) {
        _uiState.update { it.copy(userName = newName, userEmail = newEmail) }
    }
}