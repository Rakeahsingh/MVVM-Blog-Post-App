package com.example.mvvmblogpostapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mvvmblogpostapp.blog_post_features.presentation.blog_post_info.BlogPostInfoScreen
import com.example.mvvmblogpostapp.blog_post_features.presentation.blog_post_list.BlogPostItemScreen
import com.example.mvvmblogpostapp.blog_post_features.presentation.blog_post_list.BlogPostListViewModel
import com.example.mvvmblogpostapp.core.navigation.Route
import com.example.mvvmblogpostapp.ui.theme.MVVMBlogPostAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMBlogPostAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState
                    ) {


                        NavHost(navController = navController,
                            startDestination = Route.blogPostListScreen){
                            composable(Route.blogPostListScreen){


                                val viewModel = hiltViewModel<BlogPostListViewModel>()
                                val blogPost = viewModel.blogPostPagingFlow.collectAsLazyPagingItems()

                                BlogPostItemScreen(
                                    onNavigate = {
                                                 navController.navigate(it.route)
                                    },
                                    blogPostItem = blogPost,
                                    scaffoldState = scaffoldState
                                )
                            }

                            composable(
                                route = Route.blogPostDetailsScreen + "?postId={postId}",
                                arguments = listOf(
                                    navArgument("postId"){
                                        type = NavType.IntType
                                    }
                                )
                            ){
                                val postId = it.arguments?.getInt("postId")
                                if (postId != null){
                                    BlogPostInfoScreen(
                                        postId = postId,
                                        scaffoldState = scaffoldState
                                    )
                                }

                            }
                        }

                    }

                }
            }
        }
    }
}

