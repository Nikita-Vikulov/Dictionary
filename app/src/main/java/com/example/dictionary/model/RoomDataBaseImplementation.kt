package com.example.dictionary.model

import io.reactivex.Observable
import com.example.dictionary.model.DataModel
import com.example.dictionary.model.DataSource


class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}