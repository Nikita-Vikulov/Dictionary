package com.example.data

import com.example.models.AppState
import com.example.models.DataModel
import com.example.models.DataSourceLocal
import com.example.models.RepositoryLocal


class LocalRepoImpl(
    private val dataSource: DataSourceLocal<List<DataModel>>
) : RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveData(appState: AppState) {
        dataSource.saveData(appState)
    }
}