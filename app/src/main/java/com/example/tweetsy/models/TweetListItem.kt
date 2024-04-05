package com.example.tweetsy.models

// comes api in this form basicaaly list of tweetlistitem
// we have different category
data class TweetListItem(
    val category: String,
    val text: String,
)
