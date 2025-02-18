package com.bih.nic.bsphcl.trwjuc.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bih.nic.bsphcl.trwjuc.data.DtrDetail
import com.bih.nic.bsphcl.trwjuc.data.JointInspectionReport
import com.bih.nic.bsphcl.trwjuc.data.MasterRegisterDetails


/**
 *Created by Chandan Singh on 1/27/2025.
 */
@Dao
interface JointInspectionReportDao {
    @Query("SELECT * FROM JointInspectionReport")
    fun getAll(): List<JointInspectionReport>

    @Query("SELECT * FROM JointInspectionReport WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<JointInspectionReport>

//    @Query("SELECT * FROM MasterRegisterDetails WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): MasterRegisterDetails

    @Insert
    fun insertAll(vararg jointInspectionReport : JointInspectionReport)

    @Insert
    fun insert(jointInspectionReport : JointInspectionReport)

    @Delete
    fun delete(jointInspectionReport: JointInspectionReport)
}