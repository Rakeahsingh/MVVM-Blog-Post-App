package com.example.mvvmblogpostapp.blog_post_features.domain.model

import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.Content

data class BlogPost(
    val id: Int,
    val title: String,
    val comment_status: String,
    val date: String,
    val jetpack_featured_media_url: String,
    val slug: String,
)
