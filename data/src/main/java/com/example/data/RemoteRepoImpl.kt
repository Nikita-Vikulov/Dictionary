package com.example.data

import com.example.models.DataModel
import com.example.models.DataSource
import com.example.models.Repository


class RemoteRepoImpl(
    private val dataSource: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}