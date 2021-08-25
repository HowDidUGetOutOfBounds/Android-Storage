package com.example.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Tank(
    @ColumnInfo(name = "Name") val tankName: String?,
    @ColumnInfo(name = "Year of release") val tankYear: Int?,
    @ColumnInfo(name = "Nation") val tankNation: String?,
    @PrimaryKey(autoGenerate = true) val uid: Int
):Serializable
{
    constructor(name: String, year: Int, nation: String) : this
        (name, year, nation, 0)
}

