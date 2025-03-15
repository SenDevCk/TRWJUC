package com.bih.nic.bsphcl.trwjuc.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Chandan Singh on 1/27/2025.
 */
@Entity
data class MasterRegisterDetails(
    @PrimaryKey
    @NonNull // ✅ Explicitly mark as NonNull for Room
    @ColumnInfo(name = "uid")
    val uId: String, // ✅ Ensure it's non-nullable

    @ColumnInfo(name = "date_of_receiving")
    var dateOfReceiving: String?,

    @ColumnInfo(name = "place")
    var place: String?,

    @ColumnInfo(name = "capacity")
    var capacity: String?,

    @ColumnInfo(name = "date_of_testing")
    var dateOfTesting: String?,

    @ColumnInfo(name = "svr_no")
    var svrNo: String?,

    @ColumnInfo(name = "date_of_svr")
    var dateOfSvr: String?,

    @ColumnInfo(name = "date_of_issue")
    var dateOfIssue: String?,

    @ColumnInfo(name = "gate_pass_no")
    var gatePassNo: String?,

    @ColumnInfo(name = "siv_cdt_no")
    var sivCdtNo: String?,

    @ColumnInfo(name = "issued_to")
    var issuedTo: String?,

    @ColumnInfo(name = "dtr_name_plt_img")
    var dtrNamePltImg: String?,

    @ColumnInfo(name = "lt_stud_img")
    var ltStudImg: String?,

    @ColumnInfo(name = "ht_stud_img")
    var htStudImg: String?,

    @ColumnInfo(name = "remarks")
    var remarks: String?
)
