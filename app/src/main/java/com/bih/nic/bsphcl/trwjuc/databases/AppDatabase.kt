package com.bih.nic.bsphcl.trwjuc.databases


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bih.nic.bsphcl.trwjuc.dao.CircleDao
import com.bih.nic.bsphcl.trwjuc.dao.DivisionDao
import com.bih.nic.bsphcl.trwjuc.dao.DtrDetailDao
import com.bih.nic.bsphcl.trwjuc.dao.JobWiseMatUtilizationSegDao
import com.bih.nic.bsphcl.trwjuc.dao.JointInspectionReportDao
import com.bih.nic.bsphcl.trwjuc.dao.MasterRegisterDao
import com.bih.nic.bsphcl.trwjuc.dao.SectionDao
import com.bih.nic.bsphcl.trwjuc.dao.SubDivisionDao
import com.bih.nic.bsphcl.trwjuc.data.Circle
import com.bih.nic.bsphcl.trwjuc.data.Division
import com.bih.nic.bsphcl.trwjuc.data.DtrDetail
import com.bih.nic.bsphcl.trwjuc.data.JobwiseMaterialUtilizationSegment
import com.bih.nic.bsphcl.trwjuc.data.MasterRegisterDetails
import com.bih.nic.bsphcl.trwjuc.data.Section
import com.bih.nic.bsphcl.trwjuc.data.Subdivision


/**
 *Created by Chandan Singh on 1/27/2025.
 */
@Database(entities = [DtrDetail::class,MasterRegisterDetails::class,Circle::class,Division::class,Subdivision::class,Section::class,JobwiseMaterialUtilizationSegment::class, JointInspectionReportDao::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): DtrDetailDao
    abstract fun masterRegisterDao(): MasterRegisterDao
    abstract fun circleDao(): CircleDao

    abstract fun divisionDao(): DivisionDao
    abstract fun subDivisionDao(): SubDivisionDao
    abstract fun sectionDao(): SectionDao
    abstract fun jobwiseMatUtilDao(): JobWiseMatUtilizationSegDao

    abstract fun jointInspectionReportDao(): JointInspectionReportDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Singleton method to get the database instance
        fun getDatabase(context: Context): AppDatabase {
            // If the database instance is already created, return it
            return INSTANCE ?: synchronized(this) {
                // If the database instance is not yet created, create it
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "trw_db"  // Name of your database file
                )
                    .build()

                // Assign the created instance to INSTANCE variable
                INSTANCE = instance
                instance
            }
        }
    }

}