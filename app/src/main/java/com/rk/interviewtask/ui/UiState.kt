package com.rk.interviewtask.ui

import com.rk.interviewtask.model.PostsRes

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: MutableList<PostsRes>): UiState()
    data class Error(val message: String): UiState()
}