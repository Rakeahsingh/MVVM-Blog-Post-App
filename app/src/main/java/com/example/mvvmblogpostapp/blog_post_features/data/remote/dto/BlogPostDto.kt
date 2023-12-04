package com.example.mvvmblogpostapp.blog_post_features.data.remote.dto

data class BlogPostDto(

    val id: Int,
    val title: Title,
    val comment_status: String,
    val date: String,
    val jetpack_featured_media_url: String,
    val slug: String,

)