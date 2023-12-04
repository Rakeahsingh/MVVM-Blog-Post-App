package com.example.mvvmblogpostapp.blog_post_features.data.remote

import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.BlogPostDetailsDto
import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.BlogPostDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BlogPostApi {


    @GET("/wp-json/wp/v2/posts")
    suspend fun getBlogPost(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<BlogPostDto>

    @GET("/wp-json/wp/v2/posts/{postId}")
    suspend fun getBlogPostById(
        @Path("postId") postId: Int
    ): BlogPostDetailsDto

    companion object{
        const val BASE_URL = "https://blog.vrid.in/"
    }

}