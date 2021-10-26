package com.example.dictionary.presenter

import com.example.dictionary.model.AppState
import com.example.dictionary.view.View

interface Presenter<T : AppState, V : View> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}