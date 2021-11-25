package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.example.data.HistoryEntity

@Dao
interface HistoryDao {

    @Query("SELECT * from HistoryEntity")
    suspend fun all(): List<HistoryEntity>

    @Insert(onConflict = IGNORE)
    suspend fun insert(entity: HistoryEntity)

    @Update
    suspend fun update(entity: HistoryEntity)

    @Delete
    suspend fun delete(entity: HistoryEntity)
}