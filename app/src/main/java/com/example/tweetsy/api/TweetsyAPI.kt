package com.example.tweetsy.api

import com.example.tweetsy.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

/* interface defeine for retrofit, jo bhi end point hit krna ho
uske corresponding method call karunga, method ki body retorifit will provide*/
interface TweetsyAPI {

    // asyn call thats why suspend
    // get with end point.
    // header sending as parameter to category , dynamic header
    @GET("/v3/b/660fbc13acd3cb34a833b896?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>

    /*    this is for all cateogry  static header*/
    @GET("/v3/b/660fbc13acd3cb34a833b896?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories(): Response<List<String>>
}
