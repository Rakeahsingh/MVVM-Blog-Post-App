package com.example.mvvmblogpostapp.blog_post_features.presentation.blog_post_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmblogpostapp.blog_post_features.domain.use_case.BlogPostUseCase
import com.example.mvvmblogpostapp.core.utils.Resources
import com.example.mvvmblogpostapp.core.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BlogPostInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: BlogPostUseCase
):ViewModel(){

    var state by mutableStateOf(BlogPostInfoState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            val post = savedStateHandle.get<Int>("postId") ?: return@launch

            useCase(post).collect{ result ->
                when(result){
                    is Resources.Success -> {
                        state = state.copy(
                            blogPostDetail = result.data,
                            isLoading = false
                        )
                    }
                    is Resources.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                        _uiEvent.send(UiEvent.ShowSnackBar(
                            message = result.message ?: "Unknown Error"
                        ))
                    }
                    is Resources.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }

        }
    }
}