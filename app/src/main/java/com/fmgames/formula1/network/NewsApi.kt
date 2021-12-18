package com.fmgames.formula1.network

import com.fmgames.formula1.model.NewsFeed
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("/")
    fun getNews(
    ): Call<NewsFeed?>
}