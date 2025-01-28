package com.bih.nic.bsphcl.trwjuc.data

import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 1/27/2025.
 */
@Entity
class DtrDetail{
    @PrimaryKey
    val uid: Int?=null
    @ColumnInfo(name = "circle") val circle: String?=null
    @ColumnInfo(name = "division") val division: String?=null
    @ColumnInfo(name = "sub_div") val subDiv: String?=null
    @ColumnInfo(name = "section") val section: String?=null
    @ColumnInfo(name = "place") val place: String?=null
    @ColumnInfo(name = "dob") val dob: String?=null
    @ColumnInfo(name = "capacity") val capacity: String?=null
    @ColumnInfo(name = "year_of_manufacturing") val yearOfManufacturing: Int?=null
    @ColumnInfo(name = "make") val make: String?=null
    @ColumnInfo(name = "oil_capacity") val oilCapacity: String?=null
    @ColumnInfo(name = "oil_found") val oilFound: String?=null
    @ColumnInfo(name = "dtr_body_found") val dtrBodyFound: Char?=null
    @ColumnInfo(name = "ht_stud") val htStud: String?=null
    @ColumnInfo(name = "lt_stud") val ltStud: String?=null
    @ColumnInfo(name = "ht_bushing") val htBushing: String?=null
    @ColumnInfo(name = "lt_bushing") val ltBushing: String?=null
    @ColumnInfo(name = "remarks") val remarks: String?=null

}