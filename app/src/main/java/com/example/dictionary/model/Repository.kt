package com.example.dictionary.model

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}