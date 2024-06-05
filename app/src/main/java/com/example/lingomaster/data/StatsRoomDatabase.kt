package com.example.lingomaster.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(StatsData::class), version = 1, exportSchema = false)
public abstract class StatsRoomDatabase : RoomDatabase() {

    abstract fun statsDao(): StatsDao

    private class StatsRoomDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var statsDao = database.statsDao()
                    statsDao.insertStats(StatsData(0,0,0,0,"angielski"))
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: StatsRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): StatsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StatsRoomDatabase::class.java,
                    "stats_database"
                )
                    .addCallback(StatsRoomDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}