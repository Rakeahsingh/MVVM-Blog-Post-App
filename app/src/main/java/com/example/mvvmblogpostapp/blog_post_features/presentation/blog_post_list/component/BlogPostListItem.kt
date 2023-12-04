package com.example.mvvmblogpostapp.blog_post_features.presentation.blog_post_list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.mvvmblogpostapp.blog_post_features.domain.model.BlogPost

@Composable
fun BlogPostListItem(
    blogPost: BlogPost,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .clickable {
                       onClick()
            },
        elevation = 4.dp,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = blogPost.jetpack_featured_media_url,
                contentDescription = "blog_post_image",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = blogPost.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = blogPost.slug,
                fontSize = 14.sp,
                fontFamily = FontFamily.Serif,
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Comment_Status: ${blogPost.comment_status}",
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Date: ${blogPost.date}",
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis
                )

            }

        }

    }

}