package com.example.aplictrianrect.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.aplictrianrect.models.Historial

@Dao
interface HistorialDao {
    @Query("SELECT * FROM historial")
    fun getAll(): List<Historial>

    @Query("SELECT * FROM historial WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Historial>

    @Query("SELECT * FROM historial WHERE uid = 0")
    fun loadObjectToBeDeleted(): Historial

    @Insert
    fun insertAll(vararg records: Historial)

    @Delete
    fun delete(user: Historial)

}