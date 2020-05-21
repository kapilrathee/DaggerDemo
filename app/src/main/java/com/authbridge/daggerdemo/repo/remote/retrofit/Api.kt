package com.authbridge.daggerdemo.repo.remote.retrofit

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("top-headlines")
    fun getNewsList(@Query("country") country : String, @Query("apiKey") api_key : String) : Call<JsonElement>
}