package com.example.dictionary.presenter

import android.database.Observable
import com.example.dictionary.model.AppState

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}