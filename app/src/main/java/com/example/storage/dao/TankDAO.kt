package com.example.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.storage.entities.Tank

@Dao
interface TankDAO{
    @Query("Select * from tank")
    fun getAll(): List<Tank>

    @Query("SELECT * FROM tank ORDER BY :filedName")
    fun getSortedByField(filedName: String): List<Tank>

    @Insert
    fun addAll(vararg tanks: Tank)

    @Insert
    fun addTank(tank: Tank)

    @Delete
    fun delete(tank: Tank)

    @Query("DELETE FROM tank")
    fun clearAll()
}