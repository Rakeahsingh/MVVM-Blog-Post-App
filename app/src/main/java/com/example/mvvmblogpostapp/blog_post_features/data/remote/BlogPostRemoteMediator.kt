package com.example.mvvmblogpostapp.blog_post_features.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.mvvmblogpostapp.blog_post_features.data.local.BlogPostDatabase
import com.example.mvvmblogpostapp.blog_post_features.data.local.entities.BlogPostEntity
import com.example.mvvmblogpostapp.blog_post_features.data.mapper.toBlogPost
import com.example.mvvmblogpostapp.blog_post_features.data.mapper.toBlogPostEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BlogPostRemoteMediator(
    private val api: BlogPostApi,
    private val db: BlogPostDatabase
): RemoteMediator<Int, BlogPostEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BlogPostEntity>
    ): MediatorResult {
        return try {
                val loadKey = when (loadType) {
                    LoadType.REFRESH -> 1
                    LoadType.PREPEND -> return MediatorResult.Success(
                        endOfPaginationReached = true
                    )

                    LoadType.APPEND -> {
                        val lastItem = state.lastItemOrNull()
                        if (lastItem == null) {
                            1
                        } else {
                            (lastItem.id / state.config.pageSize) + 1
                        }
                    }
                }

                delay(200L)
                val blogs = api.getBlogPost(
                    page = loadKey,
                    pageCount = state.config.pageSize
                )

                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        db.dao.clearAllBlogPost()
                    }
                    val blogPostEntities = blogs.map { it.toBlogPostEntity() }
                    db.dao.upsertBlogPost(blogPostEntities)
                }

                MediatorResult.Success(
                    endOfPaginationReached = blogs.isEmpty()
                )
            } catch (e: IOException) {
                MediatorResult.Error(e)
            } catch (e: HttpException) {
                MediatorResult.Error(e)
            }

    }

}