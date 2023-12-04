package com.example.mvvmblogpostapp.blog_post_features.presentation.blog_post_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.mvvmblogpostapp.blog_post_features.data.local.entities.BlogPostEntity
import com.example.mvvmblogpostapp.blog_post_features.data.mapper.toBlogPost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BlogPostListViewModel @Inject constructor(
    pager: Pager<Int, BlogPostEntity>
): ViewModel() {

    val blogPostPagingFlow = pager.flow.map { blogPost ->
        blogPost.map {
            it.toBlogPost()
        }
    }.cachedIn(viewModelScope)

}