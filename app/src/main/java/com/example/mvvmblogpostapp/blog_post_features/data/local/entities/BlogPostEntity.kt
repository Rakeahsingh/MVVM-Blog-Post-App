package com.example.mvvmblogpostapp.blog_post_features.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.Content

@Entity
data class BlogPostEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val comment_status: String,
    val date: String,
    val jetpack_featured_media_url: String,
    val slug: String,
)
