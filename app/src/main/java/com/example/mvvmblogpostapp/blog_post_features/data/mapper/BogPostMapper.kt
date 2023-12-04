package com.example.mvvmblogpostapp.blog_post_features.data.mapper

import com.example.mvvmblogpostapp.blog_post_features.data.local.entities.BlogPostEntity
import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.BlogPostDto
import com.example.mvvmblogpostapp.blog_post_features.domain.model.BlogPost

fun BlogPostDto.toBlogPostEntity(): BlogPostEntity{
    return BlogPostEntity(
        id = id,
        title = title.rendered,
        comment_status = comment_status,
        date = date,
        jetpack_featured_media_url = jetpack_featured_media_url,
        slug = slug
    )
}

fun BlogPostEntity.toBlogPost(): BlogPost{
    return BlogPost(
        id = id,
        title= title,
        comment_status = comment_status,
        date = date,
        jetpack_featured_media_url = jetpack_featured_media_url,
        slug = slug
    )
}

