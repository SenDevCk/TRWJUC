package com.bih.nic.bsphcl.trwjuc.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Chandan Singh on 1/27/2025.
 */
@Entity
data class JointInspectionReport(
    @PrimaryKey
    @NonNull // ✅ Explicitly mark as NonNull for Room
    @ColumnInfo(name = "uid")
    val uId: String, // ✅ Ensure it's non-nullable

    @ColumnInfo(name = "circle")
    var circleId: String?,

    @ColumnInfo(name = "division")
    var divisionId: String?,

    @ColumnInfo(name = "sub_div_id")
    var subdivId: String?,

    @ColumnInfo(name = "section_id")
    var sectionId: String?,

    @ColumnInfo(name = "place")
    var place: String?,

    @ColumnInfo(name = "date_of_burning")
    var dateOfBurning: String?,

    @ColumnInfo(name = "capacity")
    var capacity: String?,

    @ColumnInfo(name = "year")
    var year: String?,

    @ColumnInfo(name = "make")
    var make: String?,

    @ColumnInfo(name = "oil_capacity")
    var oilCapacity: String?,

    @ColumnInfo(name = "oil_found")
    var oilFound: String?,

    @ColumnInfo(name = "dtr_ok")
    var dtrBodyOK: String?,

    @ColumnInfo(name = "dtr_def")
    var dtrBodyDef: String?,

    @ColumnInfo(name = "ht_stud_ok")
    var htStudOK: String?,

    @ColumnInfo(name = "ht_stud_def")
    var htStudDef: String?,

    @ColumnInfo(name = "ht_stud_miss")
    var htStudMiss: String?,

    @ColumnInfo(name = "lt_stud_ok")
    var ltStudOk: String?,

    @ColumnInfo(name = "lt_stud_def")
    var ltStudDef: String?,

    @ColumnInfo(name = "lt_stud_miss")
    var ltStudMiss: String?,

    @ColumnInfo(name = "ht_buss_ok")
    var htBushOk: String?,

    @ColumnInfo(name = "ht_buss_def")
    var htBushDef: String?,

    @ColumnInfo(name = "ht_buss_miss")
    var htBushMiss: String?,

    @ColumnInfo(name = "lt_buss_ok")
    var ltBusOk: String?,

    @ColumnInfo(name = "lt_buss_def")
    var ltBusDef: String?,

    @ColumnInfo(name = "lt_buss_miss")
    var ltBusMiss: String?,

    @ColumnInfo(name = "remarks")
    var remarks: String?
)
