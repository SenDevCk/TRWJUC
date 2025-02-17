package com.bih.nic.bsphcl.trwjuc.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *Created by Chandan Singh on 2/6/2025.
 */
@Entity
data class Section (
    @PrimaryKey
    @NonNull
    var secId : String ,
    var secName : String,
    var subDivId : String,
    var divId : String,
    var circleId : String
)