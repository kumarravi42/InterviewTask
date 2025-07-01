package com.rk.interviewtask.ui.repository

import com.rk.interviewtask.model.PostsRes
import com.rk.interviewtask.model.constant.ApiInterface
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiService: ApiInterface) {
    suspend fun getData(): List<PostsRes> = apiService.getData()
}