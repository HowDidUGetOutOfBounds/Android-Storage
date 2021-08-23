package com.example.storage.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.storage.entities.Tank

@Database(entities = arrayOf(Tank::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tankDao(): TankDAO

    companion object{
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "TankDB")
                    .build()
            }

            return INSTANCE as AppDatabase
        }
    }
}