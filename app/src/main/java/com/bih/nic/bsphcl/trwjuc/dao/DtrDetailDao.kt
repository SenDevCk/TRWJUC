package com.bih.nic.bsphcl.trwjuc.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bih.nic.bsphcl.trwjuc.data.DtrDetail


/**
 *Created by Chandan Singh on 1/27/2025.
 */
@Dao
interface DtrDetailDao {
    @Query("SELECT * FROM DtrDetail")
    fun getAll(): List<DtrDetail>

    @Query("SELECT * FROM DtrDetail WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<DtrDetail>


    @Insert
    fun insertAll(vararg users: DtrDetail)

    @Delete
    fun delete(user: DtrDetail)
}