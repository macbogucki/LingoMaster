package com.example.lingomaster.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats_table")
data class StatsData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "games") val games : Int,
    @ColumnInfo(name = "wins") val wins : Int,
    @ColumnInfo(name = "losses") val failures : Int,
    @ColumnInfo(name = "language") val language : String
)