package com.example.mvvmblogpostapp.blog_post_features.data.mapper

import com.example.mvvmblogpostapp.blog_post_features.data.remote.dto.BlogPostDetailsDto
import com.example.mvvmblogpostapp.blog_post_features.domain.model.BlogPostDetail

fun BlogPostDetailsDto.toBlogPostDetail(): BlogPostDetail{
    return BlogPostDetail(
        id = id,
        title = title.rendered,
        content = content.rendered,
        date = date,
//        jetpack_related_posts = jetpack_related_posts,
        jetpack_featured_media_url = jetpack_featured_media_url,
        slug = slug
    )
}