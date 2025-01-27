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
interface DtrDetailDao { @Query("SELECT * FROM DtrDetail")
fun getAll(): List<DtrDetail>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<DtrDetail>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): DtrDetail

    @Insert
    fun insertAll(vararg users: DtrDetail)

    @Delete
    fun delete(user: DtrDetail)
}