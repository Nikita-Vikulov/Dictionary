package com.example.dictionary.presenter

import io.reactivex.Observable
import com.example.dictionary.model.AppState

interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): io.reactivex.Observable<AppState>
}