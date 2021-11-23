package com.example.dictionary.model.room

import com.example.dictionary.model.AppState
import com.example.dictionary.model.DataSource

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}