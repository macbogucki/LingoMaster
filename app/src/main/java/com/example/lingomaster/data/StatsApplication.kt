package com.example.lingomaster.data

import android.app.Application
import androidx.room.Room.databaseBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class StatsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { StatsRoomDatabase.getDatabase(this, applicationScope) }
}