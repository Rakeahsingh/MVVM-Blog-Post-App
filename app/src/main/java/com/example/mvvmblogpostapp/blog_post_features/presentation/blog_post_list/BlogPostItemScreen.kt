package com.example.mvvmblogpostapp.blog_post_features.presentation.blog_post_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.mvvmblogpostapp.blog_post_features.domain.model.BlogPost
import com.example.mvvmblogpostapp.blog_post_features.presentation.blog_post_list.component.BlogPostListItem
import com.example.mvvmblogpostapp.core.utils.UiEvent

@Composable
fun BlogPostItemScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    blogPostItem: LazyPagingItems<BlogPost>,
//    viewModel: BlogPostListViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {

//     val blogPost = viewModel.blogPostPagingFlow.collectAsLazyPagingItems()
    
    LaunchedEffect(key1 = blogPostItem.loadState){
        if (blogPostItem.loadState.refresh is LoadState.Error){
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Unknown Error"
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        if (blogPostItem.loadState.refresh is LoadState.Loading){
            CircularProgressIndicator(
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(blogPostItem) { blog ->
                    if (blog !== null) {
                        BlogPostListItem(
                            blogPost = blog ,
                            onClick = {
                                // Navigate to Blog post Detail Screen
                            })
                    }
                }
                item{
                    if (blogPostItem.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

}