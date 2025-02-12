package com.bih.nic.bsphcl.trwjuc.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bih.nic.bsphcl.trwjuc.data.Circle
import com.bih.nic.bsphcl.trwjuc.data.DtrDetail


/**
 *Created by Chandan Singh on 2/6/2025.
 */
interface CircleDao {
    @Query("SELECT * FROM Circle")
    fun getAll(): List<Circle>

    @Insert
    fun insertAll(vararg circles: List<Circle>)

    @Delete
    fun delete(circle: Circle)

    @Query("delete FROM Circle")
    fun deleteAllCircle()
}