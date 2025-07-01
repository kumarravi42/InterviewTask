package com.rk.interviewtask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.interviewtask.model.PostsRes
import com.rk.interviewtask.ui.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {
    private val uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiStateFlow: StateFlow<UiState> = uiState

    private var currentPost = mutableListOf<PostsRes>()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            try {
                val posts = repository.getData()
                currentPost = posts.toMutableList()
                uiState.value = UiState.Success(currentPost)
            } catch (e: Exception) {
                uiState.value = UiState.Error("Failed to fetch data")
            }
        }
    }
}