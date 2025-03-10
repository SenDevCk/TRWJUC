package com.bih.nic.bsphcl.trwjuc.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bih.nic.bsphcl.trwjuc.data.Subdivision


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Dao
interface SubDivisionDao {

    @Query("SELECT * FROM Subdivision")
    fun getAllSubDivision(): List<Subdivision>

    @Query("SELECT * FROM Subdivision where circleId=(:circleId)")
    fun getSubdivisionByCircle(circleId: String): List<Subdivision>

    @Query("SELECT * FROM Subdivision where divId=(:divId)")
    fun getSubdivisionByDivision(divId: String): List<Subdivision>

    @Insert
    fun insertAll(vararg subdivisions: Subdivision)

    @Delete
    fun delete(subdivision: Subdivision)

    @Query("delete FROM Subdivision")
    fun deleteAllSubdivision()
}