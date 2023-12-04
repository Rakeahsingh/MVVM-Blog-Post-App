package com.example.mvvmblogpostapp.blog_post_features.presentation.blog_post_info

import com.example.mvvmblogpostapp.blog_post_features.domain.model.BlogPostDetail

data class BlogPostInfoState(
    val blogPostDetail: BlogPostDetail? = null,
    val isLoading: Boolean = false
)
