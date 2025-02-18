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

    @ColumnInfo(name = "date_of_birth")
    var dateOfBirth: String?,

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

    @ColumnInfo(name = "dtr_found")
    var dtrBodyFound: String?,

    @ColumnInfo(name = "htstud_found")
    var htStudFound: String?,

    @ColumnInfo(name = "ltstud_found")
    var ltStudFound: String?,

    @ColumnInfo(name = "htbussing_found")
    var htBushingFound: String?,

    @ColumnInfo(name = "ltbussing_found")
    var ltBushingStudFound: String?,

    @ColumnInfo(name = "remarks")
    var remarks: String?
)
