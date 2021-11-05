package com.example.dictionary.view

import com.example.dictionary.model.AppState

interface View {

    fun renderData(appState: AppState)
}