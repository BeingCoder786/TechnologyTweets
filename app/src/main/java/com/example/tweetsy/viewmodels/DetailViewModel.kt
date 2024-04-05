package com.example.tweetsy.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.models.TweetListItem
import com.example.tweetsy.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val tweetRepository: TweetRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val tweets: StateFlow<List<TweetListItem>>
        get() = tweetRepository.tweets

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category")
                ?: "motivation" // this is key vategory from what we have  send from category screen
            tweetRepository.getTweets(category)
        }
    }
}

/*
savestatehandle is used to
*/
