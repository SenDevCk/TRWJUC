package com.bih.nic.bsphcl.trwjuc.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Entity
class Section {
    @PrimaryKey
    var secId : String ?=null
    var secName : String ?=null
    var subDivId : String ?=null
    var divId : String ?=null
    var circleId : String ?=null
}