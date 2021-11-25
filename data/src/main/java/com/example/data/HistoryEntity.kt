package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.models.AppState
import com.example.models.DataModel

@Entity(
    indices = [Index(value = ["word"], unique = true)]
)
data class HistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "description")
    val description: String? = null
) {

    fun toDomainModel() = DataModel(
        text = word,
        null
    )
}

fun AppState.toEntity() = when (this) {
    is AppState.Success -> {
        val searchResult = this.data
        if (searchResult.isEmpty()) {
            null
        } else {
            HistoryEntity(
                searchResult.first().text.orEmpty(),
                null
            )
        }
    }
    else -> null
}