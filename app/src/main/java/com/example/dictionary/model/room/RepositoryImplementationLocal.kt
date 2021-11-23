package com.example.dictionary.model.room

import com.example.dictionary.model.AppState
import com.example.dictionary.model.DataModel
import com.example.dictionary.model.RoomDataBaseImplementation

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}

