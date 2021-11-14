package com.example.dictionary.model

interface DataSource<T> {
    suspend fun getData(word: String): T
}