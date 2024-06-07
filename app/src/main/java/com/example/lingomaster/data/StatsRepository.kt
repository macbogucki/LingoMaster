package com.example.lingomaster.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class StatsRepository(private val statsDao: StatsDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allStats: Flow<List<StatsData>> = statsDao.getAllStats()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertStats(stats: StatsData) {
        statsDao.insertStats(stats)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateStats(stats: StatsData) {
        statsDao.updateStats(stats)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun resetStats(resetStats: StatsData) {
        statsDao.updateStats(resetStats)

    }
}