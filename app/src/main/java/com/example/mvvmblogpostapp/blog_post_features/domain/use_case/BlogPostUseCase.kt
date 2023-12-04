package com.example.mvvmblogpostapp.blog_post_features.domain.use_case

import com.example.mvvmblogpostapp.blog_post_features.data.mapper.toBlogPostDetail
import com.example.mvvmblogpostapp.blog_post_features.domain.model.BlogPostDetail
import com.example.mvvmblogpostapp.blog_post_features.domain.repository.BlogPostRepository
import com.example.mvvmblogpostapp.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BlogPostUseCase @Inject constructor(
    private val repository: BlogPostRepository
){

    suspend operator fun invoke(postId: Int): Flow<Resources<BlogPostDetail>>{
        return flow {

            try {
                emit(Resources.Loading(true))
                val blog = repository.getBlogpostById(postId).toBlogPostDetail()
                emit(Resources.Success(
                    data = blog
                ))
            }catch (e: IOException){
                e.printStackTrace()
                Resources.Error(
                    null,
                    "Check Your Server"
                )
            }catch (e: HttpException){
                e.printStackTrace()
                Resources.Error(
                    null,
                    "Unknown Error"
                )
            }

        }
    }

}