package com.vivek.memerandomizer.presentation.meme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivek.memerandomizer.domain.repository.MemeRepository
import com.vivek.memerandomizer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemeViewModel @Inject constructor(
    private val repository: MemeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MemeScreenState())
    val uiState = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<MemeScreenEvent>()
    val events = _events.asSharedFlow()

    init {
        getMeme(SUBREDDIT_NAME)
    }

    fun onTriggerEvent(event: MemeScreenEvent) = viewModelScope.launch {
        when (event) {
            is MemeScreenEvent.ShowToast -> {
                _events.emit(MemeScreenEvent.ShowToast(message = event.message))
            }
            is MemeScreenEvent.ShareMeme -> {
                _events.emit(MemeScreenEvent.ShareMeme(bitmap = event.bitmap))
            }
        }
    }

    fun onRefreshMeme() {
        getMeme(SUBREDDIT_NAME)
    }

    private fun getMeme(
        subreddit: String = ""
    ) {
        viewModelScope.launch {
            repository
                .getMeme(subreddit = subreddit)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { meme ->
                                _uiState.value = uiState.value.copy(meme = meme)
                            }
                        }
                        is Resource.Error -> {
                            onTriggerEvent(
                                event = MemeScreenEvent.ShowToast(
                                    message = result.message ?: "Unknown Error!"
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _uiState.value = uiState.value.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    private companion object {
        const val SUBREDDIT_NAME = "dankmemes"
    }
}

































