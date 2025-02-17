package com.bih.nic.bsphcl.trwjuc.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bih.nic.bsphcl.trwjuc.data.Circle
import com.bih.nic.bsphcl.trwjuc.data.DtrDetail


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Dao
interface CircleDao {
    @Query("SELECT * FROM Circle")
    fun getAll(): List<Circle>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // ✅ Replaces existing entries if there's a conflict
    fun insertAll(vararg circles: Circle)

    @Delete
    fun delete(circle: Circle)

    @Query("delete FROM Circle")
    fun deleteAllCircle()

    @Query("SELECT COUNT(*) FROM Circle")
    fun countCircle() : Int
}