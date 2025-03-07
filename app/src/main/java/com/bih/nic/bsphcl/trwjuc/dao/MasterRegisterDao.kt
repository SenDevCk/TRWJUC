package com.bih.nic.bsphcl.trwjuc.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bih.nic.bsphcl.trwjuc.data.DtrDetail
import com.bih.nic.bsphcl.trwjuc.data.MasterRegisterDetails


/**
 *Created by Chandan Singh on 1/27/2025.
 */
@Dao
interface MasterRegisterDao {
    @Query("SELECT * FROM MasterRegisterDetails")
    fun getAll(): List<MasterRegisterDetails>

    @Query("SELECT * FROM MasterRegisterDetails WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<MasterRegisterDetails>

    @Query("SELECT * FROM MasterRegisterDetails WHERE uid = :userIds")
    fun loadAllById(userIds: String): MasterRegisterDetails

    @Insert
    fun insertAll(vararg masterRegisterDetails: MasterRegisterDetails)

    @Delete
    fun delete(masterRegisterDetails: MasterRegisterDetails)
}