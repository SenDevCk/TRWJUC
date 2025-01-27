package com.bih.nic.bsphcl.trwjuc.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 1/27/2025.
 */
@Entity
class MasterRegisterDetails {
    @PrimaryKey
    val uid: Int?=null
    @ColumnInfo(name = "date_of_receiving") val dateOfReceiving: String?=null
    @ColumnInfo(name = "palce") val place: String?=null
    @ColumnInfo(name = "capacity") val capacity: String?=null
    @ColumnInfo(name = "date_of_testing") val dateOfTesting: String?=null
    @ColumnInfo(name = "svr_no") val svrNo: String?=null
    @ColumnInfo(name = "date_of_svr") val dateOfSvr: String?=null
    @ColumnInfo(name = "date_of_issue") val dateOfIssue: String?=null
    @ColumnInfo(name = "gate_pass_no") val gatePassNo: Int?=null
    @ColumnInfo(name = "siv_cdt_no") val sivCdtNo: String?=null
    @ColumnInfo(name = "issued_to") val issuedTo: String?=null
    @ColumnInfo(name = "remarks") val remarks: String?=null

}