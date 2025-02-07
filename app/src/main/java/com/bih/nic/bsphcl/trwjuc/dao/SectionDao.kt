package com.bih.nic.bsphcl.trwjuc.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bih.nic.bsphcl.trwjuc.data.Section
import com.bih.nic.bsphcl.trwjuc.data.Subdivision


/**
 *Created by Chandan Singh on 2/6/2025.
 */
interface SectionDao {

    @Query("SELECT * FROM Section")
    fun getAllSection(): List<Section>

    @Query("SELECT * FROM Section where circleId=(:circleId)")
    fun getAllSectionByCircle(circleId: String): List<Section>

    @Query("SELECT * FROM Section where divId=(:divId)")
    fun getAllSectionByDivision(divId: String): List<Section>

    @Insert
    fun insertAll(vararg sections: List<Section>)

    @Delete
    fun delete(section: Section)

    @Query("delete FROM Section")
    fun deleteAllSection()
}