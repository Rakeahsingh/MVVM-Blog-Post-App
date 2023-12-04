package com.example.mvvmblogpostapp.blog_post_features.data.repository

import com.example.mvvmblogpostapp.blog_post_features.data.remote.BlogPostApi
import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.BlogPostDetailsDto
import com.example.mvvmblogpostapp.blog_post_features.domain.repository.BlogPostRepository
import javax.inject.Inject

class BlogPostRepositoryImpl @Inject constructor(
    private val api: BlogPostApi
): BlogPostRepository{
    override suspend fun getBlogpostById(postId: Int): BlogPostDetailsDto {
        return api.getBlogPostById(postId)
    }
}