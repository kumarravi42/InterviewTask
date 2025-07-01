package com.rk.interviewtask.model.constant

import com.rk.interviewtask.model.PostsRes
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun getData(): List<PostsRes>
}