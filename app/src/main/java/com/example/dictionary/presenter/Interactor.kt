package com.example.dictionary.presenter

import android.database.Observable
import com.example.dictionary.model.AppState

interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState>
}