package com.example.mvvmblogpostapp.blog_post_features.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.mvvmblogpostapp.blog_post_features.data.local.entities.BlogPostEntity

@Dao
interface BlogPostDao {

    @Upsert
    suspend fun upsertBlogPost(blogPostEntity: List<BlogPostEntity>)

    @Query("DELETE FROM blogpostentity")
    suspend fun clearAllBlogPost()

    @Query("SELECT * FROM blogpostentity")
    fun pagingSource(): PagingSource<Int, BlogPostEntity>

}