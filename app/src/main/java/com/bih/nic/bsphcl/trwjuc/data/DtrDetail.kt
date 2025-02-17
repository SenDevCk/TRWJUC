package com.bih.nic.bsphcl.trwjuc.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DtrDetail(
    @PrimaryKey
    @ColumnInfo(name = "uid")
    val uId: String, // ✅ Non-nullable

    @ColumnInfo(name = "circle")
    var circle: String,

    @ColumnInfo(name = "division")
    var division: String,

    @ColumnInfo(name = "sub_div")
    var subDiv: String,

    @ColumnInfo(name = "section")
    var section: String,

    @ColumnInfo(name = "place")
    var place: String,

    @ColumnInfo(name = "dob")
    var dob: String,

    @ColumnInfo(name = "capacity")
    var capacity: String,

    @ColumnInfo(name = "year_of_manufacturing")
    var yearOfManufacturing: Int,

    @ColumnInfo(name = "make")
    var make: String,

    @ColumnInfo(name = "oil_capacity")
    var oilCapacity: String,

    @ColumnInfo(name = "oil_found")
    var oilFound: String,

    @ColumnInfo(name = "dtr_body_found")
    var dtrBodyFound: Char,

    @ColumnInfo(name = "ht_stud")
    var htStud: String,

    @ColumnInfo(name = "lt_stud")
    var ltStud: String,

    @ColumnInfo(name = "ht_bushing")
    var htBushing: String,

    @ColumnInfo(name = "lt_bushing")
    var ltBushing: String,

    @ColumnInfo(name = "remarks")
    var remarks: String
)
