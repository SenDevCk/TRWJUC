package com.bih.nic.bsphcl.trwjuc.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bih.nic.bsphcl.trwjuc.data.Circle
import com.bih.nic.bsphcl.trwjuc.data.DtrDetail
import com.bih.nic.bsphcl.trwjuc.data.MaterialUtilized


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Dao
interface MaterialUtilizedDao {
    @Query("SELECT * FROM MaterialUtilized where trwUniqueId=:trwId")
    fun getAllMaterialUtilized(trwId:String): List<MaterialUtilized>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // ✅ Replaces existing entries if there's a conflict
    fun insertAll(vararg materialUtilized: MaterialUtilized)

    @Delete
    fun delete(materialUtilized: MaterialUtilized)

    @Query("delete FROM MaterialUtilized")
    fun deleteAllMaterialUtilized()

    @Query("SELECT COUNT(*) FROM MaterialUtilized")
    fun countMaterialUtilized() : Int
}