package com.example.mvvmblogpostapp.blog_post_features.data.remote.dto

data class BlogPostDetailsDto(

    val id: Int,
    val title: TitleX,
    val content: ContentX,
    val date: String,
//    val jetpack_related_posts: List<JetpackRelatedPostX>,
    val jetpack_featured_media_url: String,
    val slug: String,

)