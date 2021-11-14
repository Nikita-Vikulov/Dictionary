package com.example.dictionary.model

interface Repository<T> {
    suspend fun getData(word: String): T
}