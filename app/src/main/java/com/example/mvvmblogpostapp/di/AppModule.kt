package com.example.mvvmblogpostapp.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.mvvmblogpostapp.blog_post_features.data.local.BlogPostDatabase
import com.example.mvvmblogpostapp.blog_post_features.data.local.entities.BlogPostEntity
import com.example.mvvmblogpostapp.blog_post_features.data.remote.BlogPostApi
import com.example.mvvmblogpostapp.blog_post_features.data.remote.BlogPostRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideBlogPostApi(client: OkHttpClient): BlogPostApi{
        return Retrofit.Builder()
            .baseUrl(BlogPostApi.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BlogPostApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDataBase(app: Application): BlogPostDatabase{
        return Room.databaseBuilder(
            app,
            BlogPostDatabase::class.java,
            "blogPost_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBlogpostPager(
        api: BlogPostApi,
        db: BlogPostDatabase
    ): Pager<Int, BlogPostEntity>{
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = BlogPostRemoteMediator(
                api = api,
                db = db
            ),
            pagingSourceFactory = {
                db.dao.pagingSource()
            }
        )
    }

}