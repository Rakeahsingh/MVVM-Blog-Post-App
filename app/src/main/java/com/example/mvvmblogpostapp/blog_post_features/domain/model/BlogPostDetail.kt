package com.example.mvvmblogpostapp.blog_post_features.domain.model

import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.ContentX
import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.JetpackRelatedPostX
import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.TitleX

data class BlogPostDetail(
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
    val jetpack_related_posts: List<JetpackRelatedPostX>,
    val jetpack_featured_media_url: String,
    val slug: String,
)
