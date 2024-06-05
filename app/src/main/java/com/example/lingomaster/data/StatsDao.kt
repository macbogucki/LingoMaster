package com.example.lingomaster.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StatsDao{
    @Query("SELECT * FROM stats_table")
    fun getAllStats(): Flow<List<StatsData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStats(stats: StatsData)

    @Update
    suspend fun updateStats(statsData: StatsData)
}