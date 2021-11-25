package com.example.data


import com.example.models.DataModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") word: String): List<DataModel>
}