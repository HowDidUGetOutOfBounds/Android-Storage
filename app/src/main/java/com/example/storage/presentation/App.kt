package com.example.storage.presentation

import com.example.storage.dao.AppDatabase
import androidx.room.Room
import android.app.Application


class App : Application() {
    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "TanksDatabase")
            .build()
    }

    fun getDatabase(): AppDatabase? {
        return database
    }

    companion object {
        private var instance: App? = null

        fun getInstance(): App? {
            return instance
        }
    }
}