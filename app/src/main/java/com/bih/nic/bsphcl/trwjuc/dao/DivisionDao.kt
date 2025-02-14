package com.bih.nic.bsphcl.trwjuc.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bih.nic.bsphcl.trwjuc.data.Circle
import com.bih.nic.bsphcl.trwjuc.data.Division
import com.bih.nic.bsphcl.trwjuc.data.DtrDetail


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Dao
interface DivisionDao {

    @Query("SELECT * FROM Division")
    fun getAllDivision(): List<Division>

    @Query("SELECT * FROM Division where circleId=(:circleId)")
    fun getAllDivisionFromCircle(circleId: String): List<Division>

    @Insert
    fun insertAll(vararg divisions: Division)

    @Delete
    fun delete(division: Division)

    @Query("delete FROM Division")
    fun deleteAllDivision()
}