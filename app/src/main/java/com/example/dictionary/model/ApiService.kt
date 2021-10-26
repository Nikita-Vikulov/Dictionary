package com.example.dictionary.model

import io.reactivex.Observable
import com.example.dictionary.model.DataModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}