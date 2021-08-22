package com.example.storage.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storage.entities.Tank

@Database(entities = arrayOf(Tank::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tankDao(): TankDAO
}