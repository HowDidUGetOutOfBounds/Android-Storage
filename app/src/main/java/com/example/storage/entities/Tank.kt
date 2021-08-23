package com.example.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tank(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Name") val tankName: String?,
    @ColumnInfo(name = "Year of release") val tankYear: Int?,
    @ColumnInfo(name = "Nation") val tankNation: String?
)