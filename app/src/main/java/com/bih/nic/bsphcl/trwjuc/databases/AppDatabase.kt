package com.bih.nic.bsphcl.trwjuc.databases


import androidx.room.Database
import androidx.room.RoomDatabase
import com.bih.nic.bsphcl.trwjuc.dao.CircleDao
import com.bih.nic.bsphcl.trwjuc.dao.DivisionDao
import com.bih.nic.bsphcl.trwjuc.dao.DtrDetailDao
import com.bih.nic.bsphcl.trwjuc.dao.MasterRegisterDao
import com.bih.nic.bsphcl.trwjuc.dao.SectionDao
import com.bih.nic.bsphcl.trwjuc.dao.SubDivisionDao
import com.bih.nic.bsphcl.trwjuc.data.Circle
import com.bih.nic.bsphcl.trwjuc.data.Division
import com.bih.nic.bsphcl.trwjuc.data.DtrDetail
import com.bih.nic.bsphcl.trwjuc.data.MasterRegisterDetails
import com.bih.nic.bsphcl.trwjuc.data.Section
import com.bih.nic.bsphcl.trwjuc.data.Subdivision


/**
 *Created by Chandan Singh on 1/27/2025.
 */
@Database(entities = [DtrDetail::class,MasterRegisterDetails::class,Circle::class,Division::class,Subdivision::class,Section::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): DtrDetailDao
    abstract fun masterRegisterDao(): MasterRegisterDao
    abstract fun circleDao(): CircleDao

    abstract fun divisionDao(): DivisionDao
    abstract fun subDivisionDao(): SubDivisionDao
    abstract fun sectionDao(): SectionDao


}