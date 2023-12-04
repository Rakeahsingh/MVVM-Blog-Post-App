package com.example.mvvmblogpostapp.blog_post_features.domain.repository

import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.BlogPostDetailsDto


interface BlogPostRepository {

    suspend fun getBlogpostById(postId: Int): BlogPostDetailsDto

}