package com.example.mvvmblogpostapp.blog_post_features.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmblogpostapp.blog_post_features.data.local.entities.BlogPostEntity


@Database(
    entities = [BlogPostEntity::class],
    version = 1
)
abstract class BlogPostDatabase: RoomDatabase() {
    abstract val dao: BlogPostDao
}