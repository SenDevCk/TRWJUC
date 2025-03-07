package com.bih.nic.bsphcl.trwjuc.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bih.nic.bsphcl.trwjuc.data.Circle
import com.bih.nic.bsphcl.trwjuc.data.DtrDetail
import com.bih.nic.bsphcl.trwjuc.data.JobwiseMaterialUtilizationSegment


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Dao
interface JobWiseMatUtilizationSegDao {
    @Query("SELECT * FROM JobwiseMaterialUtilizationSegment")
    fun getAllMatSeg(): List<JobwiseMaterialUtilizationSegment>

    @Query("SELECT * FROM JobwiseMaterialUtilizationSegment where id=:id")
    fun getMatSegById(id:String): JobwiseMaterialUtilizationSegment
    @Insert
    fun insertAll(vararg jobs: JobwiseMaterialUtilizationSegment)

    @Delete
    fun delete(circle: JobwiseMaterialUtilizationSegment)

    @Query("delete FROM JobwiseMaterialUtilizationSegment")
    fun deleteAllCircle()
}