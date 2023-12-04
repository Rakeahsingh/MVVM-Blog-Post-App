package com.example.mvvmblogpostapp.blog_post_features.data.remote.dto

data class JetpackRelatedPostX(
    val author: String,
    val block_context: BlockContext,
    val context: String,
    val date: String,
    val excerpt: String,
    val id: Int,
    val img: ImgX,
    val title: String,
)