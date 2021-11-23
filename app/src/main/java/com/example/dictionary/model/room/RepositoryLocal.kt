package com.example.dictionary.model.room

import com.example.dictionary.model.AppState
import com.example.dictionary.model.Repository

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}