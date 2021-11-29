package com.example.repository

import com.example.model.dto.SearchResultDto
import com.example.repository.DataSource
import com.example.repository.Repository


class RepositoryImplementation(private val dataSource: DataSource<List<SearchResultDto>>) :
    Repository<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }
}
