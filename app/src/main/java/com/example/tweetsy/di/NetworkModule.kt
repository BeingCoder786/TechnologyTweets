package com.example.tweetsy.di

import com.example.tweetsy.api.TweetsyAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

// creating di module for geting retrofit object

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    // create retrofit object
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io/")
            .addConverterFactory(GsonConverterFactory.create()).build() //gson convert factory to convert response to model
    }

    // api ka object, as this is interface
    @Provides
    @Singleton
    fun provideTweesyAPi(retrofit: Retrofit): TweetsyAPI {
        return retrofit.create(TweetsyAPI::class.java)
    }
}
